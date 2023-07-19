package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {

    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDish(Long dishId);

    DishModel updateDish(DishModel dishModel);

    DishModel activeDish(DishModel dishModel);

    void deleteDish(Long dishId);

}