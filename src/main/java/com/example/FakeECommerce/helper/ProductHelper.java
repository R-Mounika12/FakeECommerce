package com.example.FakeECommerce.helper;

import com.example.FakeECommerce.entity.Category;
import com.example.FakeECommerce.entity.Product;
import com.example.FakeECommerce.exception.ExceptionHelper;
import com.example.FakeECommerce.mapper.ProductMapper;
import com.example.FakeECommerce.repository.CategoryRepository;
import com.example.FakeECommerce.repository.ProductRepository;
import com.example.FakeECommerce.request.ProductRequest;
import com.example.FakeECommerce.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductHelper {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ExceptionHelper exceptionHelper;

    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("Inside createProduct method of ProductHelper");
        try{
            Category category = getCategory(productRequest.getCategoryId());
            Product product = productMapper.mapToProduct(productRequest,category);
            Product savedProduct = productRepository.save(product);


            log.info("Product Saved Successfully");
            return productMapper.mapToProductResponse(savedProduct, category);

        }catch (Exception e) {
            log.error("Error while creating Product");
            throw new RuntimeException("Error while saving product");
        }
    }

    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        log.info("Inside updateProduct method of ProductHelper");
        try{
            Product savedProduct = getProduct(id);
            Category category = getCategory(productRequest.getCategoryId());
            Product updatedProduct = null;
            if(savedProduct != null) {
              savedProduct.setProductName(productRequest.getProductName());
              savedProduct.setDescription(productRequest.getDescription());
              savedProduct.setImage(productRequest.getImage());
              savedProduct.setPrice(productRequest.getPrice());
              savedProduct.setRating(productRequest.getRating());
              savedProduct.setCategory(category);
                updatedProduct = productRepository.save(savedProduct);
            }

            return productMapper.mapToProductResponse(updatedProduct, category);
        } catch (Exception e) {
            log.error("Error while updating Product");
            throw new RuntimeException("Error while updating Product");
        }

    }

    public ProductResponse getProductById(Long id) {
        log.info("Inside getProduct method of ProductHelper");
        try{
            Product savedProduct = getProduct(id);
            Category category = getCategory(savedProduct.getCategory().getId());
            return productMapper.mapToProductResponse(savedProduct, category);
        }catch (Exception e) {
            log.error("Error while getting Product");
            throw new RuntimeException("Error while getting Product");
        }

    }

    public List<ProductResponse> getAllProducts() {
        log.info("Inside getAllProducts method of ProductHelper");
        try {
            List<Product> products = productRepository.findAll();
            if(!CollectionUtils.isEmpty(products)) {
                return products.stream()
                        .map(product -> {
                            Category category = getCategory(product.getCategory().getId());
                            return productMapper.mapToProductResponse(product, category);
                        })
                        .toList();
            }
            return Collections.emptyList();
        }catch (Exception e) {
            log.error("Error while getting Products");
            throw new RuntimeException("Error while getting Products");
        }
    }

    public void deleteProduct(Long id) {
        log.info("Inside deleteProduct method of ProductHelper");
        try {
            productRepository.deleteById(id);
        }catch (Exception e) {
            log.error("Error while deleting Product");
            throw new RuntimeException("Error while deleting Product");
        }
    }

    private Product getProduct(Long id) {
        log.info("Inside getProduct method of ProductHelper");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        return null;
    }

    private Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null;
    }
}
