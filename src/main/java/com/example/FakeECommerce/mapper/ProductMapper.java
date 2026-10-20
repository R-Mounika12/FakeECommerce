package com.example.FakeECommerce.mapper;

import com.example.FakeECommerce.entity.Category;
import com.example.FakeECommerce.entity.Product;
import com.example.FakeECommerce.request.ProductRequest;
import com.example.FakeECommerce.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", source = "category")
    Product mapToProduct(ProductRequest productRequest, Category category);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "category", source = "category")
    ProductResponse mapToProductResponse(Product product, Category category);
}
