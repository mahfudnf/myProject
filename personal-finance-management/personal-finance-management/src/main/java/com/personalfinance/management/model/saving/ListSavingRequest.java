package com.personalfinance.management.model.saving;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListSavingRequest {
    @NotBlank
    @Size(max = 200)
    private String nameSaving;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
