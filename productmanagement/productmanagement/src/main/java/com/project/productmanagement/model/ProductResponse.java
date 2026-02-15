package com.project.productmanagement.model;

import com.project.productmanagement.entity.Category;
import com.project.productmanagement.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private Long price;

    private int stock;

    private String category;

    private String status;

    private LocalDateTime createdAt;
}
