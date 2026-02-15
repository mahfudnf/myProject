package com.project.productmanagement.model;

import com.project.productmanagement.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductRequest {

    private String name;

    private String category;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
