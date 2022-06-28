package com.student.productmanagementproject.data;

import com.student.productmanagementproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true)
    List<Product> findUserProduct(String email);

    Optional<Product> findByName(String name);
}
