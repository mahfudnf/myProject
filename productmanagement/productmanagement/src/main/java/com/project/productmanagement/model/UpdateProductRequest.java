package com.project.productmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {

    @Size(min = 1)
    private String name;

    @Positive
    private Long price;

    @PositiveOrZero
    private Integer stock;
}
