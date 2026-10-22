package com.example.FakeECommerce.validator;

import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.request.CreateOrderRequestDto;
import com.example.FakeECommerce.request.UpdateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ExceptionHelper exceptionHelper;

    public void validateUpdateOrderRequest(UpdateOrderRequestDto updateOrderRequestDto) {
        log.info("Inside validateUpdateOrderRequest method of OrderValidator");

        if(null == updateOrderRequestDto.getOrderStatus()) {
            log.error("Order status is null");
            exceptionHelper.badRequestException("Order status is null");
        }
    }

   public void validateCreateOrderRequest(CreateOrderRequestDto createOrderRequestDto) {
        log.info("Inside validateCreateOrderRequest method of OrderValidator");
        if(CollectionUtils.isEmpty(createOrderRequestDto.getOrderItemRequestDtoList())) {
            log.error("Order Items are empty");
            exceptionHelper.badRequestException("Order Items are empty");
        }
   }
}
