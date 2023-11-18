package com.example.tiendaeqiopostecnologicos.controllers;

import com.example.tiendaeqiopostecnologicos.dto.ProductToSale;
import com.example.tiendaeqiopostecnologicos.entities.Product;
import com.example.tiendaeqiopostecnologicos.entities.ProductSold;
import com.example.tiendaeqiopostecnologicos.entities.Sale;
import com.example.tiendaeqiopostecnologicos.repositories.products.ProductRepository;
import com.example.tiendaeqiopostecnologicos.repositories.products.ProductSoldRepsitory;
import com.example.tiendaeqiopostecnologicos.repositories.sales.SalesRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/sales/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ProductSoldRepsitory productSoldRepsitory;

    @PostMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable Integer id, HttpServletRequest request){
        ArrayList<ProductToSale> cart =  this.getCart(request);
        if (!cart.isEmpty() && cart.get(id) != null){
            cart.remove(id);
            this.saveCart(cart, request);
        }
        return "/sales/cart";
    }

    private void cleanCart(HttpServletRequest request) {
        this.saveCart(new ArrayList<>(), request);
    }

    @GetMapping(value = "/clean")
    public String cancelSale(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.cleanCart(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/cart/";
    }

    @PostMapping(value = "/finish")
    public String finishSale(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductToSale> carrito = this.getCart(request);
        // Si no hay carrito o está vacío, regresamos inmediatamente
        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/sales/cart";
        }
        Sale sale = salesRepository.save(new Sale());
        // Recorrer el carrito
        for (ProductToSale productToSale : carrito) {
            // Obtener el producto fresco desde la base de datos
            Product p = productRepository.findById(productToSale.getId()).orElse(null);
            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            // Le restamos existencia
            p.substractExistence(productToSale.getQuantity());
            // Lo guardamos con la existencia ya restada
            productRepository.save(p);
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            ProductSold productSold = new ProductSold(productToSale.quantity,productToSale.getPrice(),productToSale.getName(),
                    productToSale.getSKU(),sale);
            // Y lo guardamos
            productSoldRepsitory.save(productSold);
        }

        // Al final limpiamos el carrito
        this.cleanCart(request);
        // e indicamos una venta exitosa
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/cart/";
    }

    @GetMapping(value = "/cart")
    public String userInterfaceCart(Model model, HttpServletRequest request) {
        model.addAttribute("product", new Product());
        float total = 0;
        ArrayList<ProductToSale> cart = this.getCart(request);
        for (ProductToSale p: cart) total += p.getTotalSold();
        model.addAttribute("total", total);
        return "sales/cart";
    }

    private ArrayList<ProductToSale> getCart(HttpServletRequest request) {
        ArrayList<ProductToSale> cart = (ArrayList<ProductToSale>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    private void saveCart(ArrayList<ProductToSale> cart, HttpServletRequest request) {
        request.getSession().setAttribute("cart", cart);
    }

    @PostMapping(value = "/add")
    public String addAtCart(@ModelAttribute Product product, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductToSale> cart = this.getCart(request);
        Product pruductFindBySKU = productRepository.findFirstBySKU(product.getSKU());
        if (pruductFindBySKU == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " + product.getSKU() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/cart/";
        }
        if (pruductFindBySKU.isEmptyStock()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/cart/";
        }
        boolean encontrado = false;
        for (ProductToSale productToSaleActual : cart) {
            if (productToSaleActual.getSKU().equals(pruductFindBySKU.getSKU())) {
                productToSaleActual.increaseQuantity();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            cart.add(new ProductToSale(pruductFindBySKU.getName(), pruductFindBySKU.getSKU(), pruductFindBySKU.getPrice(), pruductFindBySKU.getStock(), pruductFindBySKU.getId(), 1));
        }
        this.saveCart(cart, request);
        return "redirect:/cart/";
    }
}
