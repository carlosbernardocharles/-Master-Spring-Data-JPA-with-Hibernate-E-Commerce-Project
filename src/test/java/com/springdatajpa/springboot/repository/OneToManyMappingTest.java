package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import com.springdatajpa.springboot.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    //Save order along with also save it's order items
    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("in Progress");

        //Create  order item 1
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productRepository.findById(1L).get());
        orderItem.setQuantity(2);
        orderItem.setPrice(orderItem.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem.setImageUrl("image1.png");
        order.getOrderItems().add(orderItem);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotlAmount());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("pune");
        address.setStreet("kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");

        order.setBillingAddress(address);
        orderRepository.save(order);
    }

    @Test
    void fetchOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        System.out.println(order.getStatus());
        for (OrderItem item:
             order.getOrderItems()) {
            System.out.println(item.getProduct());
        }
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }

}
