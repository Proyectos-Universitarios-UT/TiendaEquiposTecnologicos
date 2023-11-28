package com.example.tiendaeqiopostecnologicos.dto;

import com.example.tiendaeqiopostecnologicos.entities.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class ProductToSale extends Product {

    public Long quantity;

    public ProductToSale(String name, String sku, String description, Double price, Long id, Long quantity) {
        super(name, sku, description, price, id);
        this.quantity = quantity;
    }

    public Double getTotalSold(){
        return  this.getPrice() * this.quantity;
    }

    public void increaseQuantity(Long quantity) {
        this.quantity++;
    }

    public Long getQuantity() {
        return quantity;
    }
}
