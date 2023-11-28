package com.example.tiendaeqiopostecnologicos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name= "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;
    @Column(name="product_sku")
    private String SKU;
    @Column(name="product_name")
    private String name;
    @Column(name="product_description")
    private String description;
    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="especifications",
            joinColumns = @JoinColumn(name="product_id")
    )
    @Column(name="product_especifications")
    private Set<String> especifications;
    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="categories",
            joinColumns = @JoinColumn(name="product_id")
    )
    @Column(name="product_category")
    private Set<String> category;
    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="manufacturers",
            joinColumns = @JoinColumn(name="product_id")
    )
    @Column(name="product_manufacturer")
    private Set<String> manufacturer;
    @Column(name="product_image")
    private String image;
    @Column(name="product_price")
    private Double price;
    @Column(name="product_stock")
    private Long stock;
    @Column(name = "product_weight")
    private Double weight;
    @CollectionTable(
            name="dimensions",
            joinColumns = @JoinColumn(name="product_id")
    )
    @Column(name="product_dimension")
    private Set<Double> dimensions;

    private Long quantity;

    public Product(String name, String sku, String description, Double price, Long id, Long stock) {
        this.name = name;
        this.SKU = sku;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = id;
    }

    public Product(String name, String sku, Double price, Long stock) {
        this.name = name;
        this.SKU = sku;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, String sku, String description, Double price, Long id) {
        this.name = name;
        this.SKU = sku;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public void substractExistence(Long quantity){
        this.stock -= quantity;
    }

    public boolean isEmptyStock(){
        return this.stock <= 0;
    }
}
