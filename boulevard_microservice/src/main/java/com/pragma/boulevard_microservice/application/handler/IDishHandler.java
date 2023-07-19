package com.pragma.boulevard_microservice.application.handler;

import com.pragma.boulevard_microservice.application.dto.request.ActiveDishRequestDto;
import com.pragma.boulevard_microservice.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice.application.dto.request.DishUpdateRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.DishResponseDto;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.*;
import java.util.List;

public interface IDishHandler {

    public CommonResponseDto saveDish(DishRequestDto dishRequestDto);

    public List<DishResponseDto> getAllDishes();

    public DishResponseDto getDish(Long dishId);

    public CommonResponseDto updateDish(DishUpdateRequestDto dishUpdateRequestDto);

    public CommonResponseDto activeDish(ActiveDishRequestDto activeDishRequestDto);

    public void deleteDish(Long dishId);

    List<DishResponseDto> getDishesByRestaurantAndCategory(Long idRestaurant, Long idCategory, Pageable pageable);
}