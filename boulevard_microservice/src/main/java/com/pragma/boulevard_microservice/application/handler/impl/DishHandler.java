package com.pragma.boulevard_microservice.application.handler.impl;

import com.pragma.boulevard_microservice.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice.application.dto.request.DishUpdateRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.DishResponseDto;
import com.pragma.boulevard_microservice.application.handler.IDishHandler;
import com.pragma.boulevard_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice.application.mapper.IDishRequestMapper;
import com.pragma.boulevard_microservice.application.mapper.IDishResponseMapper;
import com.pragma.boulevard_microservice.domain.api.IDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort iDishServicePort;
    private final IDishRequestMapper iDishRequestMapper;
    private final IDishResponseMapper iDishResponseMapper;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Override
    public CommonResponseDto saveDish(DishRequestDto dishRequestDto) {
        return iCommonResponseMapper.toResponse(iDishServicePort.saveDish(iDishRequestMapper.toDishModel(dishRequestDto)));
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        return iDishResponseMapper.toResponseList(iDishServicePort.getAllDishes());
    }

    @Override
    public DishResponseDto getDish(Long dishId) {
        return iDishResponseMapper.toResponse(iDishServicePort.getDish(dishId));
    }

    @Override
    public CommonResponseDto updateDish(DishUpdateRequestDto dishUpdateRequestDto) {
        return iCommonResponseMapper.toResponse(iDishServicePort.updateDish(iDishRequestMapper.toDishModel(dishUpdateRequestDto)));
    }

    @Override
    public void deleteDish(Long dishId) {
        iDishServicePort.deleteDish(dishId);
    }
}