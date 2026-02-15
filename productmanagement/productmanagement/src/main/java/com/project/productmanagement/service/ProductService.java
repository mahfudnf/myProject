package com.project.productmanagement.service;

import com.project.productmanagement.entity.Category;
import com.project.productmanagement.entity.Product;
import com.project.productmanagement.entity.Status;
import com.project.productmanagement.model.*;
import com.project.productmanagement.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public Product createProduct( CreateProductRequest request){
        validationService.validate(request);

        if (productRepository.existsById(request.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the product already exists");
        }

        Product product = new Product();
        product.setId(request.getId() + UUID.randomUUID());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(resolveCategory(request.getId()));
        product.setStatus(resolveStatus(request.getStock()));
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse get(String id){
        Product product = productRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"product not found"));

        return toProductResponse(product);
    }

    @Transactional
    public Page<ProductResponse> search(SearchProductRequest request){
        Specification<Product> specification = (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getName())){
                predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("name"),"%"+request.getName()+"%")));
            }

            if (Objects.nonNull(request.getCategory())){
                predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("category"),"%"+request.getCategory()+"%")));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        }));

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Product> products = productRepository.findAll(specification,pageable);
        List<ProductResponse> productResponses = products.getContent().stream().
                map(this::toProductResponse).
                toList();

        return new PageImpl<>(productResponses,pageable,products.getTotalElements());
    }

    @Transactional
    public UpdateProductResponse update(String id,UpdateProductRequest request){
        validationService.validate(request);

        Product product = productRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"product not found"));

        if (Objects.nonNull(request.getName())){
            product.setName(request.getName());
        }

        if (Objects.nonNull(request.getPrice())){
            product.setPrice(request.getPrice());
        }

        if (Objects.nonNull(request.getStock())){
            product.setStock(request.getStock());
        }

        if (Objects.nonNull(request.getName()) || Objects.nonNull(request.getPrice())|| Objects.nonNull(request.getStock())){
            product.setUpdateAt(LocalDateTime.now());
        }

        productRepository.save(product);

        return UpdateProductResponse.builder().name(product.getName()).price(product.getPrice()).stock(product.getStock()).build();
    }

    @Transactional
    public void delete(String id){
        Product product = productRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"product not found"));

        productRepository.delete(product);
    }

    // Method Helper
     private Category resolveCategory(String code){
        if (code.equalsIgnoreCase("F")){
            return Category.FOOD;
        } else if (code.equalsIgnoreCase("D")) {
            return Category.DRINK;
        }else {
            throw  new IllegalStateException("Invalid category code");
        }
     }

     private Status resolveStatus(int stock){
        if (stock > 0 ){
            return Status.ACTIVE;
        } else if (stock <= 0) {
            return Status.INACTIVE;
        }else {
            throw new IllegalStateException("Invalid stock");
        }
     }

     private ProductResponse toProductResponse(Product product){
         return ProductResponse.builder().
                 id(product.getId()).
                 name(product.getName()).
                 price(product.getPrice()).
                 stock(product.getStock()).
                 category(String.valueOf(product.getCategory())).
                 status(String.valueOf(product.getStatus())).
                 createdAt(product.getCreatedAt()).
                 build();
     }
}
