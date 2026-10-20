package com.example.FakeECommerce.validator;

import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.request.ProductRequest;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class ProductValidator {

    private final ExceptionHelper exceptionHelper;

    public void validateProductRequest(ProductRequest productRequest) {
        if(null == productRequest) {
            exceptionHelper.badRequestException("Product request is null");
        }
        if(null == productRequest.getProductName() || StringUtils.isBlank(productRequest.getProductName())) {
            exceptionHelper.badRequestException("Product name is null or empty");
        }
        if(null == productRequest.getCategoryId() || null == productRequest.getPrice()) {
            exceptionHelper.badRequestException("Product category id/ price is null or empty");
        }
    }
}
