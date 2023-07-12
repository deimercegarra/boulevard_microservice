package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {

    void saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDish(Long dishId);

    void updateDish(DishModel dishModel);

    void deleteDish(Long dishId);

}