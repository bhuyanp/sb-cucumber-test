package com.example.sbtest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class Product implements Comparable<Product> {
    private long id;
    @NotBlank(message = "Title is required")
    private String title;
    @Min(value = 0, message = "The price must be positive")
    private double price;

    @Override
    public int compareTo(Product o) {
        return Long.compare(o.getId(), id);
    }

}
