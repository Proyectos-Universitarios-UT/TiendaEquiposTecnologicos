package com.example.tiendaeqiopostecnologicos.services.impl;

import com.example.tiendaeqiopostecnologicos.entities.Product;
import com.example.tiendaeqiopostecnologicos.repositories.products.ProductRepository;
import com.example.tiendaeqiopostecnologicos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Long saveProduct(Product product) {
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public Product decreaseStock(Integer quantitySold, String sku) {
        Product product = productRepository.findFirstBySKU(sku);
        Integer newStock = Math.toIntExact(quantitySold - product.getStock());
        product.setStock(Long.valueOf(newStock));
        return product;
    }


}
