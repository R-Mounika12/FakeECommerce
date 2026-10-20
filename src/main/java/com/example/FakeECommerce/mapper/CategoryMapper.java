package com.example.FakeECommerce.mapper;

import com.example.FakeECommerce.entity.Category;
import com.example.FakeECommerce.request.CategoryRequest;
import com.example.FakeECommerce.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapToCategory(CategoryRequest categoryRequest);

    CategoryResponse mapToCategoryResponse(Category category);
}
