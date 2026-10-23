package com.example.FakeECommerce.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {

    private Long id;

    private String comment;

    private BigDecimal rating;

    private Long orderId;

    private Long productId;

    private String productName;
}
