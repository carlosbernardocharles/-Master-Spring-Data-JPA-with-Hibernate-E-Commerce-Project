package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPQLQueriesTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionJPQLIndexParamMethod(){
        Product prod = productRepository.findByNameOrDescriptionJPQLIndexParam("Product 2", "Product 2 Description");
        System.out.println(prod.toString());
    }

    @Test
    void findByNameOrDescriptionJPQLNamedParam(){
        Product prod = productRepository.findByNameOrDescriptionJPQLNamedParam("product 1", "Produc");
        System.out.println(prod.toString());
    }
}
