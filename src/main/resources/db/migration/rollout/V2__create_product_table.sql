CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image VARCHAR(255),
    category_id BIGINT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    rating DECIMAL(10,2),
    created_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    modified_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT true,
    FOREIGN KEY (category_id) REFERENCES categories(id)
    );