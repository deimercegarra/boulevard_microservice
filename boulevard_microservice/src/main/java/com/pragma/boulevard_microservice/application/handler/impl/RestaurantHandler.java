package com.pragma.boulevard_microservice.application.handler.impl;

import com.pragma.boulevard_microservice.application.dto.request.RestaurantRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantResponseDto;
import com.pragma.boulevard_microservice.application.handler.IRestaurantHandler;
import com.pragma.boulevard_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice.application.mapper.IRestaurantRequestMapper;
import com.pragma.boulevard_microservice.application.mapper.IRestaurantResponseMapper;
import com.pragma.boulevard_microservice.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort iRestaurantServicePort;
    private final IRestaurantRequestMapper iRestaurantRequestMapper;
    private final IRestaurantResponseMapper iRestaurantResponseMapper;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Override
    public CommonResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        return iCommonResponseMapper.toResponse(iRestaurantServicePort.saveRestaurant(iRestaurantRequestMapper.toRestaurantModel(restaurantRequestDto)));
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return iRestaurantResponseMapper.toResponseList(iRestaurantServicePort.getAllRestaurants());
    }

    @Override
    public RestaurantResponseDto getRestaurant(Long restaurantId) {
        return iRestaurantResponseMapper.toResponse(iRestaurantServicePort.getRestaurant(restaurantId));
    }

    @Override
    public void updateRestaurant(RestaurantRequestDto restaurantRequestDto) {
        iRestaurantServicePort.saveRestaurant(iRestaurantRequestMapper.toRestaurantModel(restaurantRequestDto));
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        iRestaurantServicePort.deleteRestaurant(restaurantId);
    }
}