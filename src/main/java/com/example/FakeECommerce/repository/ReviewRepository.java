package com.example.FakeECommerce.repository;

import com.example.FakeECommerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.product JOIN FETCH r.order WHERE r.id = :id")
    Optional<Review> findByIdWithRelations(Long id);

    @Query("SELECT r FROM Review r JOIN FETCH r.product JOIN FETCH r.order")
    List<Review> findAllWithRelations();

    @Query("SELECT r FROM Review r JOIN FETCH r.product JOIN FETCH r.order WHERE r.product.id = :productId")
    List<Review> findByProductId(Long productId);

    @Query("SELECT r FROM Review r JOIN FETCH r.product JOIN FETCH r.order WHERE r.order.id = :orderId")
    List<Review> findByOrderId(Long orderId);
}
