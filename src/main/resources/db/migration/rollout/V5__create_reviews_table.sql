CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(1000),
    rating DECIMAL(3, 2) NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT true,
    CONSTRAINT fk_review_order FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES products (id)
);
