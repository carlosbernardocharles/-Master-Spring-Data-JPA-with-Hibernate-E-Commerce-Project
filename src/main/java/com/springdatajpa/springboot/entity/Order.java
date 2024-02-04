package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    /*One To One mapping (unidirectional)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id",referencedColumnName = "id")

     */
    //The Order entity is the source of the relationship because it owns the association and manages the persistence of the relationship.
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;


    //Default Fetch type for many is Lazy
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "order")//add mappedBy to One to Many
   // @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotlAmount(){
        BigDecimal amount = new BigDecimal(0.0);
        for(OrderItem item : this.orderItems){
            amount = amount.add(item.getPrice());
        }
        return amount;
    }

}
