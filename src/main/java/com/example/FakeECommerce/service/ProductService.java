package com.example.FakeECommerce.service;

import com.example.FakeECommerce.helper.ProductHelper;
import com.example.FakeECommerce.request.ProductRequest;
import com.example.FakeECommerce.response.ProductResponse;
import com.example.FakeECommerce.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductValidator productValidator;
    private final ProductHelper productHelper;

    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("Inside createProduct method of ProductService");
        productValidator.validateProductRequest(productRequest);
        ProductResponse product = productHelper.createProduct(productRequest);
        log.info("Product created successfully");
        return product;

    }

    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        log.info("Inside updateProduct method of ProductService");
        productValidator.validateProductRequest(productRequest);
        ProductResponse product = productHelper.updateProduct(productRequest, id);
        log.info("Product updated successfully");
        return product;
    }

    public ProductResponse getProduct(Long id) {
        log.info("Inside getProduct method of ProductService");
        ProductResponse product = productHelper.getProductById(id);
        log.info("Product retrieved successfully");
        return product;
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Inside getAllProducts method of ProductService");
        List<ProductResponse> products = productHelper.getAllProducts();
        log.info("Products are retrieved successfully");
        return products;
    }

    public void deleteProduct(Long id) {
        log.info("Inside deleteProduct method of ProductService");
        productHelper.deleteProduct(id);
        log.info("Product delete successfully");
    }


}
