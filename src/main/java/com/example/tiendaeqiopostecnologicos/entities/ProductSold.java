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
    private Integer quantityProducts;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    public ProductSold(Integer quantity, Double price, String name, String sku, Sale sale) {
        this.quantityProducts = quantity;
        this.price = price;
        this.name = name;
        this.SKU = sku;
        this.sale = sale;
    }
}
