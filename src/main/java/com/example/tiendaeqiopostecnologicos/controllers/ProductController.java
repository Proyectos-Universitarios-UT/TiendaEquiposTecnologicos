package com.example.tiendaeqiopostecnologicos.controllers;

import com.example.tiendaeqiopostecnologicos.entities.Product;
import com.example.tiendaeqiopostecnologicos.repositories.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/seller/show")
    public String getProducts(Model model){
        model.addAttribute(productRepository.findAll());
        return "products/list_products";
    }

    @GetMapping("/winemaker/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "products/add_product";
    }
    @PostMapping("/winemaker/delete")
    public String deleteProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Delete succesfully!")
                .addFlashAttribute("class", "warning");
        productRepository.deleteById(product.getId());
        return "redirect:/products/list_products";
    }

    @PostMapping("/winemaker/edit/{id}")
    public String updateProduct(@ModelAttribute @Validated Product product,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            if (product.getId() != null){
                return "products/edit_product";
            }
            return "redirect:/products/list_products";
        }

        Product posibleExist = productRepository.findFirstBySKU(product.getSKU());

        if (posibleExist != null && !posibleExist.getId().equals(product.getId())){
            redirectAttributes.addFlashAttribute("message", "Product with this code exist!")
                    .addFlashAttribute("class", "warning");
            return "redirect:/products/add_product";
        }
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "Porduct edited succesfully!")
                .addFlashAttribute("class", "warning");
        return "redirect:/products/list_products";
    }

    @GetMapping("/winemaker/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).orElse(null));
        return "products/edit_product";
    }

    public String saveProduct(@ModelAttribute @Validated Product product,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "products/add_product";
        }
        if (productRepository.findFirstBySKU(product.getSKU()) != null){
            redirectAttributes.addFlashAttribute("message", "Product with this code exist!")
                    .addFlashAttribute("class", "warning");
            return "redirect:/products/winemaker/add";
        }
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("message", "Porduct add succesfully!")
                .addFlashAttribute("class", "warning");
        return "redirect:/products/winemaker/add";
    }
}
