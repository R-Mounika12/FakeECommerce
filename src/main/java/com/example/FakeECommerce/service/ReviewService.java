package com.example.FakeECommerce.service;

import com.example.FakeECommerce.helper.ReviewHelper;
import com.example.FakeECommerce.request.ReviewRequest;
import com.example.FakeECommerce.response.ReviewResponse;
import com.example.FakeECommerce.validator.ReviewValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewValidator reviewValidator;
    private final ReviewHelper reviewHelper;

    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        log.info("Inside createReview method of ReviewService");
        reviewValidator.validateReviewRequest(reviewRequest);
        ReviewResponse reviewResponse = reviewHelper.createReview(reviewRequest);
        log.info("Review created successfully");
        return reviewResponse;
    }

    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        log.info("Inside updateReview method of ReviewService");
        reviewValidator.validateReviewRequest(reviewRequest);
        ReviewResponse reviewResponse = reviewHelper.updateReview(id, reviewRequest);
        log.info("Review updated successfully");
        return reviewResponse;
    }

    public ReviewResponse getReview(Long id) {
        log.info("Inside getReview method of ReviewService");
        ReviewResponse reviewResponse = reviewHelper.getReviewById(id);
        log.info("Review retrieved successfully");
        return reviewResponse;
    }

    public List<ReviewResponse> getAllReviews() {
        log.info("Inside getAllReviews method of ReviewService");
        List<ReviewResponse> reviews = reviewHelper.getAllReviews();
        log.info("Reviews retrieved successfully");
        return reviews;
    }

    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        log.info("Inside getReviewsByProductId method of ReviewService");
        List<ReviewResponse> reviews = reviewHelper.getReviewsByProductId(productId);
        log.info("Reviews retrieved successfully for product id {}", productId);
        return reviews;
    }

    public List<ReviewResponse> getReviewsByOrderId(Long orderId) {
        log.info("Inside getReviewsByOrderId method of ReviewService");
        List<ReviewResponse> reviews = reviewHelper.getReviewsByOrderId(orderId);
        log.info("Reviews retrieved successfully for order id {}", orderId);
        return reviews;
    }

    public void deleteReview(Long id) {
        log.info("Inside deleteReview method of ReviewService");
        reviewHelper.deleteReview(id);
        log.info("Review deleted successfully");
    }
}
