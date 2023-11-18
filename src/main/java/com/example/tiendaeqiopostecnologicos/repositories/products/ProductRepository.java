package com.example.tiendaeqiopostecnologicos.repositories.products;

import com.example.tiendaeqiopostecnologicos.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findFirstBySKU(String sku);
}
