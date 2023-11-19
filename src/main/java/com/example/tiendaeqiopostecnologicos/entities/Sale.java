package com.example.tiendaeqiopostecnologicos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;
    @Column(name = "sale_date")
    private LocalDateTime dateSale;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @Column(name = "sale_products")
    private Set<ProductSold> products;

    public Double getTotal(){
        Double total = 0.0;
        for (ProductSold prod : products) {
            total += prod.getTotal();
        }
        return total;
    }

}
