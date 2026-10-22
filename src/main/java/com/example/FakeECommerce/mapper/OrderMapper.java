package com.example.FakeECommerce.mapper;

import com.example.FakeECommerce.entity.Order;
import com.example.FakeECommerce.entity.OrderProducts;
import com.example.FakeECommerce.response.OrderItemResponseDto;
import com.example.FakeECommerce.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "orderStatus", source = "order.orderStatus")
    @Mapping(target = "orderItems", source = "orderProducts")
    OrderResponseDto mapToOrderResponseDto(Order order, List<OrderProducts> orderProducts);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "productPrice", source = "product.price")
    @Mapping(target = "productImage", source = "product.image")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "totalPrice", expression = "java(orderProducts.getProduct().getPrice().multiply(java.math.BigDecimal.valueOf(orderProducts.getQuantity())))")
    OrderItemResponseDto mapToOrderItemResponseDto(OrderProducts orderProducts);


}
