package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //load all Spring boot context
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("Product 1 Description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        //Save product
        Product saveObject = productRepository.save(product);


        //display product info
        System.out.println(saveObject.getId());
        System.out.println(saveObject.toString());
    }

    @Test
    void updateUsingSaveMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        product.setName("updated product 1");
        product.setDescription("Updated product 1 Desc");
        productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        System.out.println(product);
    }

    @Test
    void saveAllMethod(){

        Product product = new Product();
        product.setName("product 1");
        product.setDescription("Product 1 Description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product1.png");


        Product product3 = new Product();
        product3.setName("product 2");
        product3.setDescription("Product 2 Description");
        product3.setSku("200ABC");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product2.png");

        productRepository.saveAll(List.of(product,product3));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        products.forEach((product) ->{
            System.out.println(product.getName());
        });
    }

    @Test
    void deleteByIdMethod(){
        Long id = 1L;
        productRepository.deleteById(id);
    }


    @Test
    void  deleteMethod(){
        //
        Product product = productRepository.findById(6L).get();

        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){
        Product product = productRepository.findById(9L).get();
        Product product2 = productRepository.findById(10L).get();
        productRepository.deleteAll(List.of(product2,product));
    }

    @Test
    void countMethod(){
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsById(){
        boolean b = productRepository.existsById(11L);
        System.out.println(b);
    }




}