package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.ICategoryServicePort;
import com.pragma.boulevard_microservice.domain.model.CategoryModel;
import com.pragma.boulevard_microservice.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public void saveCategory(CategoryModel categoryModel) {
        iCategoryPersistencePort.saveCategory(categoryModel);
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return iCategoryPersistencePort.getAllCategories();
    }

    @Override
    public CategoryModel getCategory(Long userId) {
        return iCategoryPersistencePort.getCategory(userId);
    }

    @Override
    public void updateCategory(CategoryModel categoryModel) {
        iCategoryPersistencePort.updateCategory(categoryModel);
    }

    @Override
    public void deleteCategory(Long userId) {
        iCategoryPersistencePort.deleteCategory(userId);
    }
}