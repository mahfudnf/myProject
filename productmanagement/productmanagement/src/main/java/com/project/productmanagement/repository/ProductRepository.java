package com.project.productmanagement.repository;

import com.project.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {

}
