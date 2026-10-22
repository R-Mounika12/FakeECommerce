package com.example.FakeECommerce.service;

import com.example.FakeECommerce.helper.OrderHelper;
import com.example.FakeECommerce.request.CreateOrderRequestDto;
import com.example.FakeECommerce.request.UpdateOrderRequestDto;
import com.example.FakeECommerce.response.OrderResponseDto;
import com.example.FakeECommerce.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderValidator orderValidator;
    private final OrderHelper orderHelper;

    public OrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) {
        log.info("Inside createOrder method of OrderService");
        log.debug("Validating the create order request");
        orderValidator.validateCreateOrderRequest(createOrderRequestDto);
        return orderHelper.createOrder(createOrderRequestDto);

    }

    public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto, Long id) {
        log.info("Inside updateOrder method of OrderService");
        log.debug("Validating the update order request");
        orderValidator.validateUpdateOrderRequest(updateOrderRequestDto);
        return orderHelper.updateOrder(id, updateOrderRequestDto);

    }

    public OrderResponseDto getOrder(Long id) {
        log.info("Inside getOrder method of OrderService");
        OrderResponseDto orderResponseDto = orderHelper.getOrderById(id);
        log.info("Order retrieved successfully");
        return orderResponseDto;
    }

    public List<OrderResponseDto> getOrders() {
        log.info("Inside getOrders method of OrderService");
        List<OrderResponseDto> orderResponseDtoList = orderHelper.getAllOrders();
        log.info("Orders retrieved successfully");
        return orderResponseDtoList;
    }

    public void deleteOrder(Long id) {
        log.info("Inside deleteOrder method of OrderService");
        orderHelper.deleteOrder(id);
        log.info("Order deleted successfully");
    }
}
