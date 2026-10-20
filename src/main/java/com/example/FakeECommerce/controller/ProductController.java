package com.example.FakeECommerce.controller;

import com.example.FakeECommerce.request.ProductRequest;
import com.example.FakeECommerce.response.ApiResponse;
import com.example.FakeECommerce.response.ProductResponse;
import com.example.FakeECommerce.service.ProductService;
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
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest) {
        log.info("Inside createProduct method of ProductController");
        ProductResponse product = productService.createProduct(productRequest);
        log.info("Product created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(product, "product created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        log.info("Inside getProduct method of ProductController");
        ProductResponse productResponse = productService.getProduct(id);
        log.info("Product retrieved successfully");
        return ResponseEntity.ok(ApiResponse.success(productResponse, "product retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        log.info("Inside updateProduct method of ProductController");
        ProductResponse productResponse = productService.updateProduct(productRequest, id);
        log.info("Product updated successfully");
        return ResponseEntity.ok(ApiResponse.success(productResponse, "product updated successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        log.info("Inside getAllProducts method of ProductController");
        List<ProductResponse> productResponse = productService.getAllProducts();
        log.info("Product retrieved successfully");
        return ResponseEntity.ok(ApiResponse.success(productResponse, "products retrieved successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProduct(@PathVariable Long id) {
        log.info("Inside deleteProduct method of ProductController");
        productService.deleteProduct(id);
        log.info("Product deleted successfully");
        return ResponseEntity.ok(ApiResponse.success(null, "product deleted successfully"));
    }
}
