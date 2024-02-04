package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUniDirectionalMappingTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void savedOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setTotalQuantity(5);
        order.setStatus("In progress");
        order.setTotalPrice(new BigDecimal(1000));

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
    void getOrderMethod(){
        Order order = orderRepository.findById(2L).get();
        System.out.println(order.toString());
    }


    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        order.setStatus("Delivered");
        order.getBillingAddress().setZipCode("411087");

        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }


}
