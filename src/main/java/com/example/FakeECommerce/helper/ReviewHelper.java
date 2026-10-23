package com.example.FakeECommerce.helper;

import com.example.FakeECommerce.entity.Order;
import com.example.FakeECommerce.entity.Product;
import com.example.FakeECommerce.entity.Review;
import com.example.FakeECommerce.mapper.ReviewMapper;
import com.example.FakeECommerce.repository.OrderRepository;
import com.example.FakeECommerce.repository.ProductRepository;
import com.example.FakeECommerce.repository.ReviewRepository;
import com.example.FakeECommerce.request.ReviewRequest;
import com.example.FakeECommerce.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewHelper {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        log.info("Inside createReview method of ReviewHelper");
        Order order = getOrder(reviewRequest.getOrderId());
        Product product = getProduct(reviewRequest.getProductId());
        Review review = reviewMapper.mapToReview(reviewRequest, order, product);
        review.setIsActive(true);
        Review savedReview = reviewRepository.save(review);
        log.info("Review saved successfully");
        return reviewMapper.mapToReviewResponse(savedReview, order, product);
    }

    @Transactional
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        log.info("Inside updateReview method of ReviewHelper");
        Review review = getReview(id);
        Order order = getOrder(reviewRequest.getOrderId());
        Product product = getProduct(reviewRequest.getProductId());
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setOrder(order);
        review.setProduct(product);
        Review updatedReview = reviewRepository.save(review);
        log.info("Review updated successfully");
        return reviewMapper.mapToReviewResponse(updatedReview, order, product);
    }

    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long id) {
        log.info("Inside getReviewById method of ReviewHelper");
        Review review = getReview(id);
        return reviewMapper.mapToReviewResponse(review, review.getOrder(), review.getProduct());
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews() {
        log.info("Inside getAllReviews method of ReviewHelper");
        List<Review> reviews = reviewRepository.findAllWithRelations();
        if (CollectionUtils.isEmpty(reviews)) {
            return Collections.emptyList();
        }
        return reviews.stream()
                .map(review -> reviewMapper.mapToReviewResponse(review, review.getOrder(), review.getProduct()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        log.info("Inside getReviewsByProductId method of ReviewHelper");
        getProduct(productId);
        List<Review> reviews = reviewRepository.findByProductId(productId);
        if (CollectionUtils.isEmpty(reviews)) {
            return Collections.emptyList();
        }
        return reviews.stream()
                .map(review -> reviewMapper.mapToReviewResponse(review, review.getOrder(), review.getProduct()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByOrderId(Long orderId) {
        log.info("Inside getReviewsByOrderId method of ReviewHelper");
        getOrder(orderId);
        List<Review> reviews = reviewRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(reviews)) {
            return Collections.emptyList();
        }
        return reviews.stream()
                .map(review -> reviewMapper.mapToReviewResponse(review, review.getOrder(), review.getProduct()))
                .toList();
    }

    @Transactional
    public void deleteReview(Long id) {
        log.info("Inside deleteReview method of ReviewHelper");
        Review review = getReview(id);
        review.setIsActive(false);
        reviewRepository.save(review);
        log.info("Review soft-deleted successfully");
    }

    private Review getReview(Long id) {
        return reviewRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }
}
