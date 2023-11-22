package com.example.tiendaeqiopostecnologicos.dto;

import com.example.tiendaeqiopostecnologicos.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductToSale extends Product {

    public Integer quantity;

    public ProductToSale(String name, String sku, Double price, Integer stock, Long id, int i) {
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
