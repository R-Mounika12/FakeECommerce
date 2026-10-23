package com.example.FakeECommerce.mapper;

import com.example.FakeECommerce.entity.Order;
import com.example.FakeECommerce.entity.Product;
import com.example.FakeECommerce.entity.Review;
import com.example.FakeECommerce.request.ReviewRequest;
import com.example.FakeECommerce.response.ReviewResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "comment", source = "reviewRequest.comment")
    @Mapping(target = "rating", source = "reviewRequest.rating")
    @Mapping(target = "order", source = "order")
    @Mapping(target = "product", source = "product")
    Review mapToReview(ReviewRequest reviewRequest, Order order, Product product);

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "comment", source = "review.comment")
    @Mapping(target = "rating", source = "review.rating")
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.productName")
    ReviewResponse mapToReviewResponse(Review review, Order order, Product product);
}
