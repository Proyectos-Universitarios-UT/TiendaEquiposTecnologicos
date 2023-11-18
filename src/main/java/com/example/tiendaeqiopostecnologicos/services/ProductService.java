package com.example.tiendaeqiopostecnologicos.services;

import com.example.tiendaeqiopostecnologicos.entities.Product;

public interface ProductService {
    Long saveProduct(Product product);
    Product decreaseStock(Integer quantitySold, String sku);
}