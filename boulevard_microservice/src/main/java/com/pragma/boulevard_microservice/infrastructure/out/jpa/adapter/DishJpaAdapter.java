package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IDishPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.boulevard_microservice.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.boulevard_microservice.infrastructure.exception.UnauthorizedUserException;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;
    private final ICategoryRepository iCategoryRepository;
    private final IRestaurantRepository iRestaurantRepository;

    @Override
    public DishModel saveDish(DishModel dishModel) {

        DishEntity dishEntity = iDishEntityMapper.toEntity(dishModel);

        dishEntity.setCategoryEntity(iCategoryRepository.findById(dishModel.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new));

        dishEntity.setRestaurantEntity(iRestaurantRepository.findById(dishModel.getRestaurantId())
                .orElseThrow(RestaurantNotFoundException::new));

        dishModel.setActive(true);

        return iDishEntityMapper.toModel(iDishRepository.save(dishEntity));
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> entityList = iDishRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iDishEntityMapper.toModelList(entityList);
    }

    @Override
    public DishModel getDish(Long userId) {
        return iDishEntityMapper.toModel(iDishRepository.findById(userId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public DishModel updateDish(DishModel dishModel) {
        DishEntity dishEntity = iDishRepository.getReferenceById(dishModel.getId());

        RestaurantEntity restaurantEntity = iRestaurantRepository.getReferenceById(dishEntity.getRestaurantEntity().getId());

        if (! (dishModel.getUserId().equals(restaurantEntity.getIdOwner())) )
            throw new UnauthorizedUserException("Unauthorized user.");

        dishEntity.setDescription(dishModel.getDescription());
        dishEntity.setPrice(String.valueOf( dishModel.getPrice() ));

        return iDishEntityMapper.toModel(iDishRepository.save(dishEntity));
    }

    @Override
    public DishModel activeDish(DishModel dishModel) {
        DishEntity dishEntity = iDishRepository.getReferenceById(dishModel.getId());

        RestaurantEntity restaurantEntity = iRestaurantRepository.getReferenceById(dishEntity.getRestaurantEntity().getId());

        if (! (dishModel.getUserId().equals(restaurantEntity.getIdOwner())) )
            throw new UnauthorizedUserException("Unauthorized user.");

        dishEntity.setActive(dishModel.isActive());

        return iDishEntityMapper.toModel(iDishRepository.save(dishEntity));
    }

    @Override
    public void deleteDish(Long userId) {
        iDishRepository.deleteById(userId);
    }

}