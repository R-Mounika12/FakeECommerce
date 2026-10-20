package com.example.FakeECommerce.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String productName;

    private String description;

    private String image;

    private Long categoryId;

    private BigDecimal price;

    private BigDecimal rating;
}
