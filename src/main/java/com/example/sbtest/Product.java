package com.example.sbtest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private long id;
    @NotBlank(message = "Title is required")
    private String title;
    @Min(value = 0, message = "The price must be positive")
    private double price;
}
