package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
/*
//Using IndexParam
@NamedQuery(
        name = "Product.findByPrice",
        query = "SELECT p FROM Product p WHERE p.price = ?1"
)*/

/*
//NamedParam
@NamedQuery(
        name = "Product.findByPrice",
        query = "SELECT p FROM Product p WHERE p.price = :price"
)

 */

@NamedQueries(
        {
                @NamedQuery(name = "Product.findAllOrderByNameDesc",
                        query = "SELECT p FROM Product p ORDER By p.name DESC"
                ),
                @NamedQuery(
                        name = "Product.findByPrice",
                        query = "SELECT p FROM Product p WHERE p.price = :price"
                )
        }
)
/*
@NamedNativeQuery(
        name = "Product.findByDescription",
        query = "SELECT * FROM products p WHERE p.description = :description",
        resultClass = Product.class
)*/

@NamedNativeQueries(
        {
                @NamedNativeQuery(
                       name = "Product.findAllOrderByNameASC",
                       query = "SELECT * FROM products ORDER BY name ASC",
                        resultClass = Product.class
                ),
                @NamedNativeQuery(
                        name = "Product.findByDescription",
                        query = "SELECT * FROM products p WHERE p.description = :description",
                        resultClass = Product.class
                )
        }
)

@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit"
                )
        }
)
public class Product {
    public Product(String sku, String name, String description, BigDecimal price, boolean active, String imageUrl, LocalDateTime dateTime, LocalDateTime lastUpdate) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.imageUrl = imageUrl;
        this.dateCreated = dateTime;
        this.lastUpdate = lastUpdate;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequenceName",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "stock_keeping_unit",nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


    //ManyToOne
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private ProductCategory category;



}
