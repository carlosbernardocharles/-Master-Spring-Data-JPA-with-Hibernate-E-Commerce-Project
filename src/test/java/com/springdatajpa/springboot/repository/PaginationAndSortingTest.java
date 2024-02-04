package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void pagination(){
        int pageNo = 0;
        int pageSize = 5;

        //Create pageable object
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        //findAll method and pass pageable object
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        products.forEach(product -> {
            System.out.println(product.toString());
        });


        //Total pages
        int totalPages = page.getTotalPages();
        //totalElements
        long totalElements = page.getTotalElements();
//        numberOfElements
        int numberOfElements = page.getNumberOfElements();
//        size
        int size = page.getSize();
        //First
        boolean first = page.isFirst();
        //last
        boolean last = page.isLast();

        System.out.println("Total pages: "+totalPages);
        System.out.println("total Elements: "+totalElements);
        System.out.println("numberOfElements: "+numberOfElements);
        System.out.println("size: "+size);
        System.out.println("is First? :"+first);
        System.out.println("is Last? :"+last);
    }

    //Sorting
    @Test
    void sorting(){
        String sortBy = "price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        List<Product> products = productRepository.findAll(Sort.by(sortBy).ascending());
        products.forEach(product -> {
            System.out.println(product.toString());
        });
    }


    @Test
    void sortingByMultipleFields(){
        //Tips these value bellow should come from the client
        String sortBy = "name";
        String sortByDesc = "description";
        String sortDir = "desc";

        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Sort sortByDescription = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByDesc).ascending(): Sort.by(sortByDesc).descending();

        Sort groupBySort = sortByName.and(sortByDescription);
        List<Product> productsSorted = productRepository.findAll(groupBySort);

        productsSorted.forEach(product -> {
            System.out.println(product.toString());
        });
    }

    @Test
    void paginationAndSortingTogether(){
        String sortByPrice = "price";
        String sortDir = "desc";
        int pageNo = 0;
        int pageSize = 5;

        //Sort Object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortByPrice).ascending() : Sort.by(sortByPrice).descending();

        //Pageable Object
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> content = page.getContent();
        content.forEach(product -> {
            System.out.println(product.toString());
        });

        boolean first = page.isFirst();
        System.out.println("Is first :"+first);

        boolean last = page.isLast();
        System.out.println("is Last: "+last);

        int size = page.getSize();
        System.out.println("Size: "+size);

        long totalElements = page.getTotalElements();
        System.out.println("Total Elements: "+totalElements);

        int totalPages = page.getTotalPages();
        System.out.println("Total pages: "+totalPages);
    }
}
