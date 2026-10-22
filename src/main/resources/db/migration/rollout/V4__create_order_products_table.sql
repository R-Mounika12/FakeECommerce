
CREATE TABLE IF NOT EXISTS order_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    created_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    modified_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT true,
    CONSTRAINT fk_order_product FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_product_order FOREIGN KEY (product_id) REFERENCES products (id)
    );