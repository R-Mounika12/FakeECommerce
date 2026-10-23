package com.example.FakeECommerce.validator;

import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.request.ReviewRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewValidator {

    private final ExceptionHelper exceptionHelper;

    public void validateReviewRequest(ReviewRequest reviewRequest) {
        log.info("Inside validateReviewRequest method of ReviewValidator");
        if (reviewRequest == null) {
            log.error("Review request is null");
            throw new RuntimeException(exceptionHelper.badRequestException("Review request is null").getMessage());
        }
        if (reviewRequest.getOrderId() == null) {
            log.error("Order id is null");
            throw new RuntimeException(exceptionHelper.badRequestException("Order id is null").getMessage());
        }
        if (reviewRequest.getProductId() == null) {
            log.error("Product id is null");
            throw new RuntimeException(exceptionHelper.badRequestException("Product id is null").getMessage());
        }
        if (reviewRequest.getRating() == null) {
            log.error("Rating is null");
            throw new RuntimeException(exceptionHelper.badRequestException("Rating is null").getMessage());
        }
        if (reviewRequest.getRating().compareTo(BigDecimal.ZERO) < 0
                || reviewRequest.getRating().compareTo(BigDecimal.valueOf(5)) > 0) {
            log.error("Rating must be between 0 and 5");
            throw new RuntimeException(exceptionHelper.badRequestException("Rating must be between 0 and 5").getMessage());
        }
    }
}
