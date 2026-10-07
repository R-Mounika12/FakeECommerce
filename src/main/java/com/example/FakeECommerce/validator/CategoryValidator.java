package com.example.FakeECommerce.validator;

import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.request.CategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryValidator {

    private final ExceptionHelper exceptionHelper;

    public void validateCategoryRequest(CategoryRequest categoryRequest) {
        log.info("Inside validateCategoryRequest method of CategoryHelper");
        if(null != categoryRequest && null != categoryRequest.getCategoryName()) {
            log.error("CategoryName is null or empty");
            exceptionHelper.badRequestException("CategoryName is null or empty");
        }
        log.info("Exiting validateCategoryRequest method of CategoryValidator");
    }
}
