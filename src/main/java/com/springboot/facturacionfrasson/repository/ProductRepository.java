package com.springboot.facturacionfrasson.repository;

import com.springboot.facturacionfrasson.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
