package com.example.tiendaeqiopostecnologicos.dto;

import com.example.tiendaeqiopostecnologicos.entities.Product;

public class ProductToSale extends Product {

    public Integer quantity;

    public ProductToSale(String name, String sku, Double price, Integer stock, Long id, Integer quantity) {
//        super(name,sku,price,stock);
    }

    public ProductToSale(String name, String sku, Double price, Integer stock, Integer quantity) {
//        super(name,sku,price,stock);
    }

    public Double getTotalSold(){
        return  this.getPrice() * this.quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
