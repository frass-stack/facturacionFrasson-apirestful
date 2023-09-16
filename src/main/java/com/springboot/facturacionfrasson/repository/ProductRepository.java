package com.springboot.facturacionfrasson.repository;

import com.springboot.facturacionfrasson.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> findByIsActiveTrue();
}
