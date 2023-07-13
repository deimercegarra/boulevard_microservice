package com.pragma.boulevard_microservice.application.handler;

import com.pragma.boulevard_microservice.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    public CommonResponseDto saveDish(DishRequestDto dishRequestDto);

    public List<DishResponseDto> getAllDishes();

    public DishResponseDto getDish(Long dishId);

    public void updateDish(DishRequestDto dishRequestDto);

    public void deleteDish(Long dishId);

}