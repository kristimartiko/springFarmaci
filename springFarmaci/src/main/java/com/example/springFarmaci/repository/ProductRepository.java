package com.example.springFarmaci.repository;

import com.example.springFarmaci.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products WHERE to_date IS NULL", nativeQuery = true)
    public List<Product> findAll();

    @Modifying
    @Query(value = "UPDATE products SET quantity = quantity -:quantity1 WHERE id =:id", nativeQuery = true)
    public void LowerProductQuantityOnPurchase(Integer quantity1, Long id);
}
