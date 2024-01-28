package com.enviro.assessment.grad001.philanimsibi.repository;

import com.enviro.assessment.grad001.philanimsibi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
