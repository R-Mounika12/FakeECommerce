package com.example.FakeECommerce.response;

import com.example.FakeECommerce.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private String description;

    private String image;

    private Category category;

    private BigDecimal price;

    private BigDecimal rating;
}
