package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodsTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findByName(){
        Product product = productRepository.findByName("product 1");
        System.out.println(product);
    }

    @Test
    void findByNameAndDescription(){
        Product byNameAndDescription = productRepository.findByNameAndDescription("product 2", "Product 2 Description");
        System.out.println(byNameAndDescription);
    }

    @Test
    void findDistinctByName(){
        Product distinctByName = productRepository.findDistinctByName("Product 2");
        System.out.println(distinctByName);
    }

    @Test
    void findByGreaterThanPrice(){
        List<Product> byPriceGreaterThan = productRepository.findByPriceGreaterThan(new BigDecimal("200.0"));
        byPriceGreaterThan.forEach(product -> {
            System.out.println(product.toString());
        });
    }

    @Test
    void findByPriceLessThan(){
        List<Product> byPriceLessThan = productRepository.findByPriceLessThan(new BigDecimal("500.0"));
        byPriceLessThan.forEach(product -> {
            System.out.println(product.toString());
        });
    }
    @Test
    void findByNameContaining(){
        List<Product> produc = productRepository.findByNameContaining("produc");
        produc.forEach(product -> {
            System.out.println(product.toString());
        });
    }

    @Test
    void findByNameLike(){
        List<Product> pro = productRepository.findByNameLike("product 2");
        pro.forEach(product -> {
            System.out.println(product.getName());
        });
    }

    @Test
    void findByPriceBetween(){
        List<Product> byPriceBetween = productRepository.findByPriceBetween(new BigDecimal("100.00"), new BigDecimal("400.00"));
        byPriceBetween.forEach(product -> {
            System.out.println(product.toString());
        });
    }
    @Test
    void findByDateCreatedBetween(){
        List<Product> byDateCreatedBetween = productRepository.findByDateCreatedBetween(
                LocalDateTime.of(2024, 01, 25, 10, 10),
                LocalDateTime.of(2024, 01, 28, 10, 10));
        byDateCreatedBetween.forEach(byDate ->{
            System.out.println(byDate.toString());
        });

    }

    @Test
    void findByNameIn(){
        List<Product> byNameIn = productRepository.findByNameIn(List.of("product 2", "product 10"));
        byNameIn.forEach(name -> {
            System.out.println(name.toString());
        });
    }

    @Test
    void findTop4ByName(){
        List<Product> product = productRepository.findTop4ByName("product 2");
        product.forEach(product1 -> {
            System.out.println(product1.toString());
        });
    }
}
