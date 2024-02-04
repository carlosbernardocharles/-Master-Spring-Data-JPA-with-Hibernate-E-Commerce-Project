package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Product findByNameAndDescription(String name,String description);
    Product findDistinctByName(String name);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    List<Product> findByDateCreatedBetween(LocalDateTime dateTimeInit, LocalDateTime dateTimeLast);
    List<Product> findByNameIn(List<String> names);
    List<Product> findTop4ByName(String name);

    //Define JPQL query using @Query annotation with index or positions parameters
    //Tips: when we use @Query annotation we don't need to follow the naming convention on our method
    @Query("SELECT p FROM Product p where p.name = ?1 or p.description = ?2")
    Product findByNameOrDescriptionJPQLIndexParam(String name, String description);

    //Define JPQL query using @Query annotation with named parameters
    @Query("SELECT p FROM Product p where p.name = :name or p.description = :description")
    Product findByNameOrDescriptionJPQLNamedParam(@Param("name") String name,
                                                  @Param("description") String description);

    //Define Native  SQL Query with Index parameters
    @Query(value = "SELECT * FROM products p WHERE p.name = ?1 OR p.description = ?2", nativeQuery = true)
    Product findByNameOrDescriptionSQLIndexParam(String name, String description);

    //Define Native SQL Query using @Query annotation with named Parameters
    @Query(value = "SELECT * FROM products p WHERE p.name = :name OR p.description = :description",
                                                    nativeQuery = true)
    Product findByNameOrDescriptionSQLNamedParam(@Param("name") String name,
                                                 @Param("description") String description);

    //Define Named JPQL query using Index
    //Product findByPrice(BigDecimal price);
    //Using namedParam
    Product findByPrice(@Param("price") BigDecimal price);
    List<Product> findAllOrderByNameDesc();


    //Define Named native SQL query
    @Query(nativeQuery = true)
    Product findByDescription(@Param("description") String description);


    @Query(nativeQuery = true)
    List<Product> findAllOrderByNameASC();

}

