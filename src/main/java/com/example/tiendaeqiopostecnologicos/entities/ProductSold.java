package com.example.tiendaeqiopostecnologicos.entities;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "product_sold")
@RequiredArgsConstructor
public class ProductSold {
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
    @Column(name="product_price")
    private Double price;
    @Column(name="product_stock")
    private Long quantityProducts;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    public ProductSold(Long quantity, Double price, String name, String sku, String description, Sale sale) {
        this.quantityProducts = quantity;
        this.price = price;
        this.name = name;
        this.SKU = sku;
        this.description = description;
        this.sale = sale;
    }

    public ProductSold(String name, Double price, String sku, Sale sale) {
        this.name = name;
        this.price = price;
        this.SKU = sku;
        this.sale = sale;
    }

    public Double getTotal(){
        return this.quantityProducts * this.price;
    }
}
