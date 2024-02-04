package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import com.springdatajpa.springboot.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    void saveProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("books");
        productCategory.setDescriptionName("books Description");

        Product product1 = new Product();
        product1.setName("Core java");
        product1.setPrice(new BigDecimal("1000"));
        product1.setImageUrl("image1.png");
        product1.setSku("ABCD");
        product1.setActive(true);
        product1.setCategory(productCategory);
        productCategory.getProducts().add(product1);

        Product product2 = new Product();
        product2.setName("Effective java");
        product2.setPrice(new BigDecimal("2000"));
        product2.setImageUrl("image2.png");
        product2.setSku("ABCDE");
        product2.setActive(true);
        product2.setCategory(productCategory);
        productCategory.getProducts().add(product2);
        productCategoryRepository.save(productCategory);

    }

    @Test
    @Transactional
    void fetchCategory(){
        ProductCategory productCategory = productCategoryRepository.findById(1L).get();
        System.out.println(productCategory.getProducts());
    }

}