package com.project.productmanagement.controller;

import com.project.productmanagement.entity.Category;
import com.project.productmanagement.entity.Product;
import com.project.productmanagement.model.*;
import com.project.productmanagement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
            path = "/api/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> createProduct(@RequestBody CreateProductRequest request){
        productService.createProduct(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/products/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> get(@PathVariable String id){
        ProductResponse productResponse = productService.get(id);
        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @GetMapping(
            path = "/api/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> search(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "category",required = false) String category,
            @RequestParam(value = "page",required = false,defaultValue = "0") Integer page,
            @RequestParam(value = "size",required = false,defaultValue = "10") Integer size){

        SearchProductRequest request = SearchProductRequest.builder()
                .page(page)
                .size(size)
                .name(name)
                .category(category)
                .build();

        Page<ProductResponse> productResponses = productService.search(request);
        return WebResponse.<List<ProductResponse>>builder()
                .data(productResponses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(productResponses.getNumber())
                        .totalPage(productResponses.getTotalPages())
                        .size(productResponses.getSize())
                        .build())
                .build();
    }

    @PatchMapping(
            path = "/api/products/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UpdateProductResponse> update(@PathVariable String id, @RequestBody UpdateProductRequest request){

        UpdateProductResponse updateProductResponse = productService.update(id,request);
        return WebResponse.<UpdateProductResponse>builder().data(updateProductResponse).build();
    }

    @DeleteMapping(
            path = "/api/products/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("id") String id){
        productService.delete(id);
        return WebResponse.<String>builder().data("OK").build();
    }

}
