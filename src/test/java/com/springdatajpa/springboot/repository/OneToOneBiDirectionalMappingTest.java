package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBiDirectionalMappingTest {
    @Autowired
    AddressRepository addressRepository;

    @Test
    void saveAddressMethod(){
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
        address.setOrder(order);
        addressRepository.save(address);

    }

    @Test
    void updateAddressMethod(){
        Address address = addressRepository.findById(3L).get();
        address.setZipCode("411048");

        address.getOrder().setStatus("Delivered");
        addressRepository.save(address);

    }

    @Test
    void fetchAddressMethod(){
        Address address = addressRepository.findById(3L).get();
    }

    @Test
    void deleteAddressMethod(){
        addressRepository.deleteById(3L);
    }
}
