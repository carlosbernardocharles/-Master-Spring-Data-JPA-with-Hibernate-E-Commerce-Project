package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NativeSQLQueriesTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionSQLIndexParamMethod(){
        Product prod = productRepository.findByNameOrDescriptionSQLIndexParam("Product 3 Description", "Product 3 Description");
        System.out.println(prod.toString());

    }

    @Test
    void findByNameOrDescriptionSQLNamedParam(){
        Product prod = productRepository.findByNameOrDescriptionSQLNamedParam("Product 3 Description", "Product 3 Description");
        System.out.println(prod.toString());
    }

}
