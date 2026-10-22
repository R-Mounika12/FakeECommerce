package com.example.FakeECommerce.controller;

import com.example.FakeECommerce.request.CreateOrderRequestDto;
import com.example.FakeECommerce.request.UpdateOrderRequestDto;
import com.example.FakeECommerce.response.ApiResponse;
import com.example.FakeECommerce.response.OrderResponseDto;
import com.example.FakeECommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        log.info("Inside createOrder method of OrderController");
        OrderResponseDto orderResponseDto = orderService.createOrder(createOrderRequestDto);
        log.info("Order created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(orderResponseDto, "Order created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderById(@PathVariable Long id) {
        log.info("Inside getOrderById method of OrderController");
        OrderResponseDto orderResponseDto = orderService.getOrder(id);
        log.info("Order retrieved successfully");
        return ResponseEntity.ok(ApiResponse.success(orderResponseDto, "Order retrieved successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getAllOrders() {
        log.info("Inside getAllOrders method of OrderController");
        List<OrderResponseDto> orderResponseDtoList = orderService.getOrders();
        log.info("Orders retrieved successfully");
        return ResponseEntity.ok(ApiResponse.success(orderResponseDtoList, "Orders retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        log.info("Inside updateOrder method of OrderController");
        OrderResponseDto orderResponseDto = orderService.updateOrder(updateOrderRequestDto, id);
        log.info("Order updated successfully");
        return ResponseEntity.ok(ApiResponse.success(orderResponseDto, "Order updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> deleteOrder(@PathVariable Long id) {
        log.info("Inside deleteOrder method of OrderController");
        orderService.deleteOrder(id);
        log.info("Order deleted successfully");
        return ResponseEntity.ok(ApiResponse.success(null, "Order deleted successfully"));
    }
}
