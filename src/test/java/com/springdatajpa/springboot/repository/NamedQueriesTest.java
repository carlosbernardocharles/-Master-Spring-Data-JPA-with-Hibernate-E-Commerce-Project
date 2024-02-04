package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class NamedQueriesTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void namedJPQLQuery(){
        Product product = productRepository.findByPrice(new BigDecimal(200));
        System.out.println(product.toString());
    }

    @Test
    void namedJPQLQueries(){
        List<Product> allOrderByNameDesc = productRepository.findAllOrderByNameDesc();
        allOrderByNameDesc.forEach(p -> {
            System.out.println(p.toString());
        });
    }

    @Test
    void namedNativeSQLQuery(){
        Product byDescription = productRepository.findByDescription("Product 2 Description");
        System.out.println(byDescription.toString());

    }
    @Test
    void namedNativeSQLQueries(){
        Product byDescription = productRepository.findByDescription("Product 2 Description");
        System.out.println(byDescription.toString());
        List<Product> allOrderByASC = productRepository.findAllOrderByNameASC();
        allOrderByASC.forEach(orderBy ->{
            System.out.println(orderBy.toString());
        });
    }



}
