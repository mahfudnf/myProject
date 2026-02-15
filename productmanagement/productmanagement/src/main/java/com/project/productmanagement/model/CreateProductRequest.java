package com.project.productmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private Long price;

    private int stock;

}
