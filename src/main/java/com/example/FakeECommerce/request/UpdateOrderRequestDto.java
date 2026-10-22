package com.example.FakeECommerce.request;

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
public class UpdateOrderRequestDto {

    private OrderStatus orderStatus;

    private List<OrderItemActionDto> orderItems;
}
