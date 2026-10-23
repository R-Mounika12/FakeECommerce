package com.example.FakeECommerce.controller;

import com.example.FakeECommerce.request.ReviewRequest;
import com.example.FakeECommerce.response.ApiResponse;
import com.example.FakeECommerce.response.ReviewResponse;
import com.example.FakeECommerce.service.ReviewService;
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
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(@RequestBody ReviewRequest reviewRequest) {
        log.info("Inside createReview method of ReviewController");
        ReviewResponse reviewResponse = reviewService.createReview(reviewRequest);
        log.info("Review created successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(reviewResponse, "Review created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReviews() {
        log.info("Inside getAllReviews method of ReviewController");
        List<ReviewResponse> reviews = reviewService.getAllReviews();
        log.info("Reviews retrieved successfully");
        return ResponseEntity.ok(ApiResponse.success(reviews, "Reviews retrieved successfully"));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewsByProductId(@PathVariable Long productId) {
        log.info("Inside getReviewsByProductId method of ReviewController");
        List<ReviewResponse> reviews = reviewService.getReviewsByProductId(productId);
        log.info("Reviews retrieved successfully for product id {}", productId);
        return ResponseEntity.ok(ApiResponse.success(reviews, "Reviews retrieved successfully"));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewsByOrderId(@PathVariable Long orderId) {
        log.info("Inside getReviewsByOrderId method of ReviewController");
        List<ReviewResponse> reviews = reviewService.getReviewsByOrderId(orderId);
        log.info("Reviews retrieved successfully for order id {}", orderId);
        return ResponseEntity.ok(ApiResponse.success(reviews, "Reviews retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable Long id) {
        log.info("Inside getReviewById method of ReviewController");
        ReviewResponse reviewResponse = reviewService.getReview(id);
        log.info("Review retrieved successfully");
        return ResponseEntity.ok(ApiResponse.success(reviewResponse, "Review retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(@PathVariable Long id,
                                                                    @RequestBody ReviewRequest reviewRequest) {
        log.info("Inside updateReview method of ReviewController");
        ReviewResponse reviewResponse = reviewService.updateReview(id, reviewRequest);
        log.info("Review updated successfully");
        return ResponseEntity.ok(ApiResponse.success(reviewResponse, "Review updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponse>> deleteReview(@PathVariable Long id) {
        log.info("Inside deleteReview method of ReviewController");
        reviewService.deleteReview(id);
        log.info("Review deleted successfully");
        return ResponseEntity.ok(ApiResponse.success(null, "Review deleted successfully"));
    }
}
