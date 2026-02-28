package com.project.productmanagement.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.productmanagement.entity.Category;
import com.project.productmanagement.entity.Product;
import com.project.productmanagement.entity.Status;
import com.project.productmanagement.model.*;
import com.project.productmanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        productRepository.deleteAll();
    }

    @Test
    void testCreateProductSuccess() throws Exception{
        CreateProductRequest request = new CreateProductRequest();
        request.setId("F");
        request.setName("Bakso");
        request.setPrice(10000L);
        request.setStock(10);

        mockMvc.perform(
                post("/api/products").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("OK",response.getData());
        });
    }

    @Test
    void testCreateProductBadRequest() throws Exception{
        CreateProductRequest request = new CreateProductRequest();
        request.setId("");
        request.setName("");
        request.setPrice(null);
        request.setStock(0);

        mockMvc.perform(
                post("/api/products").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testCreateProductDuplicate() throws Exception{
        Product product = new Product();
        product.setId("D");
        product.setName("Degan");
        product.setPrice(10000L);
        product.setStock(5);
        product.setCategory(Category.DRINK);
        product.setStatus(Status.ACTIVE);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);

        CreateProductRequest request = new CreateProductRequest();
        request.setId("D");
        request.setName("Degan");
        request.setPrice(10000L);
        request.setStock(5);

        mockMvc.perform(
                post("/api/products").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testGetProductNotFound() throws Exception{
        mockMvc.perform(
                get("/api/products/S").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testGetProductSuccess() throws Exception{
        Product product = new Product();
        product.setId("D" + UUID.randomUUID());
        product.setName("Degan");
        product.setPrice(10000L);
        product.setStock(5);
        product.setCategory(Category.DRINK);
        product.setStatus(Status.ACTIVE);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);

        mockMvc.perform(
                get("/api/products/" + product.getId()).
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(product.getId(),response.getData().getId());
            assertEquals(product.getName(),response.getData().getName());
            assertEquals(product.getPrice(),response.getData().getPrice());
            assertEquals(product.getStock(),response.getData().getStock());
            assertEquals(product.getCategory().name(),response.getData().getCategory());
            assertEquals(product.getStatus().name(),response.getData().getStatus());
            assertNotNull(response.getData().getCreatedAt());
        });
    }

    @Test
    void testSearchNotFound() throws Exception{
        mockMvc.perform(
                get("/api/products").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ProductResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(0,response.getData().size());
            assertEquals(0,response.getPaging().getTotalPage());
            assertEquals(0,response.getPaging().getCurrentPage());
            assertEquals(10,response.getPaging().getSize());

        });
    }

    @Test
    void testSearchSuccess() throws Exception{

        for (int i = 0 ; i<100 ; i++){
            Product product = new Product();
            product.setId("D" + UUID.randomUUID());
            product.setName("Degan"+i);
            product.setPrice(10000L);
            product.setStock(5);
            product.setCategory(Category.DRINK);
            product.setStatus(Status.ACTIVE);
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdateAt(LocalDateTime.now());
            productRepository.save(product);
        }


        mockMvc.perform(
                get("/api/products").
                        queryParam("name","Degan").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ProductResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(10,response.getData().size());
            assertEquals(10,response.getPaging().getTotalPage());
            assertEquals(0,response.getPaging().getCurrentPage());
            assertEquals(10,response.getPaging().getSize());

        });

        mockMvc.perform(
                get("/api/products").
                        queryParam("category","DRINK").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ProductResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(10,response.getData().size());
            assertEquals(10,response.getPaging().getTotalPage());
            assertEquals(0,response.getPaging().getCurrentPage());
            assertEquals(10,response.getPaging().getSize());

        });
    }

    @Test
    void testUpdateProductBadRequest() throws Exception{
        Product product = new Product();
        product.setId("D"+UUID.randomUUID());
        product.setName("Degan");
        product.setPrice(10000L);
        product.setStock(5);
        product.setCategory(Category.DRINK);
        product.setStatus(Status.ACTIVE);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setName("");

        mockMvc.perform(
                patch("/api/products/" + product.getId()).
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testUpdateProductSuccess() throws Exception{
        Product product = new Product();
        product.setId("D"+UUID.randomUUID());
        product.setName("Degan");
        product.setPrice(10000L);
        product.setStock(5);
        product.setCategory(Category.DRINK);
        product.setStatus(Status.ACTIVE);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setName("Americano");
        request.setPrice(15000L);
        request.setStock(10);

        mockMvc.perform(
                patch("/api/products/" + product.getId()).
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UpdateProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(request.getName(),response.getData().getName());
            assertEquals(request.getPrice(),response.getData().getPrice());
            assertEquals(request.getStock(),response.getData().getStock());
        });
    }

    @Test
    void testDeleteProductNotFound() throws Exception{
        mockMvc.perform(
                delete("/api/products/S333333").
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void testDeleteProductSuccess() throws Exception{
        Product product = new Product();
        product.setId("D"+UUID.randomUUID());
        product.setName("Degan");
        product.setPrice(10000L);
        product.setStock(5);
        product.setCategory(Category.DRINK);
        product.setStatus(Status.ACTIVE);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);

        mockMvc.perform(
                delete("/api/products/" + product.getId()).
                        accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("OK",response.getData());
        });
    }
}
