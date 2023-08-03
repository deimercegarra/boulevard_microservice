package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishServicePort {

    List<DishModel> getDishesByRestaurantAndCategory(Long idRestaurant, Long idCategory, Pageable pageable);

    CommonResponseModel saveDish(DishModel dishModel);

    CommonResponseModel updateDish(DishModel dishModel);

    CommonResponseModel activeDish(DishModel dishModel);

}