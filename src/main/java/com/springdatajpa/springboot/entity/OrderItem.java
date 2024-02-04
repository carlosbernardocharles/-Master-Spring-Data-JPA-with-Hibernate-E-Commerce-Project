package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;
    private BigDecimal price;
    private int Quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    //Bi Directional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;
}
