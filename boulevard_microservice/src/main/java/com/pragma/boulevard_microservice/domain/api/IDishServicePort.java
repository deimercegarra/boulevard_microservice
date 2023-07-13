package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {

    CommonResponseModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDish(Long dishId);

    CommonResponseModel updateDish(DishModel dishModel);

    void deleteDish(Long dishId);

}