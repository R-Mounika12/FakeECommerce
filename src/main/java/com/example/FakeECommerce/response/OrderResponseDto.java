package com.example.FakeECommerce.response;

import com.example.FakeECommerce.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;

    private OrderStatus orderStatus;

    private List<OrderItemResponseDto> orderItems;

}
