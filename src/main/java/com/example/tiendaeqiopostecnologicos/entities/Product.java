package com.example.tiendaeqiopostecnologicos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name= "products")
@RequiredArgsConstructor
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
    private Integer stock;
    @Column(name = "product_weight")
    private Double weight;
    @CollectionTable(
            name="dimensions",
            joinColumns = @JoinColumn(name="product_id")
    )
    @Column(name="product_dimension")
    private Set<Double> dimensions;

    public void substractExistence(Integer quantity){
        this.stock -= quantity;
    }

    public boolean isEmptyStock(){
        return this.stock <= 0;
    }
}
