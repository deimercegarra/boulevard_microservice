package com.pragma.boulevard_microservice.application.handler.impl;

import com.pragma.boulevard_microservice.application.dto.request.CategoryRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CategoryResponseDto;
import com.pragma.boulevard_microservice.application.handler.ICategoryHandler;
import com.pragma.boulevard_microservice.application.mapper.ICategoryRequestMapper;
import com.pragma.boulevard_microservice.application.mapper.ICategoryResponseMapper;
import com.pragma.boulevard_microservice.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort iCategoryServicePort;
    private final ICategoryRequestMapper iCategoryRequestMapper;
    private final ICategoryResponseMapper iCategoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        iCategoryServicePort.saveCategory(iCategoryRequestMapper.toCategoryModel(categoryRequestDto));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return iCategoryResponseMapper.toResponseList(iCategoryServicePort.getAllCategories());
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        return iCategoryResponseMapper.toResponse(iCategoryServicePort.getCategory(categoryId));
    }

    @Override
    public void updateCategory(CategoryRequestDto categoryRequestDto) {
        iCategoryServicePort.saveCategory(iCategoryRequestMapper.toCategoryModel(categoryRequestDto));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        iCategoryServicePort.deleteCategory(categoryId);
    }
}