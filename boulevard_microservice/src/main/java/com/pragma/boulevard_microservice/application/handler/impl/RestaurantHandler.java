package com.pragma.boulevard_microservice.application.handler.impl;

import com.pragma.boulevard_microservice.application.dto.request.RestaurantRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantPageableResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantResponseDto;
import com.pragma.boulevard_microservice.application.handler.IRestaurantHandler;
import com.pragma.boulevard_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice.application.mapper.IRestaurantRequestMapper;
import com.pragma.boulevard_microservice.application.mapper.IRestaurantResponseMapper;
import com.pragma.boulevard_microservice.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort iRestaurantServicePort;
    private final IRestaurantRequestMapper iRestaurantRequestMapper;
    private final IRestaurantResponseMapper iRestaurantResponseMapper;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Transactional
    @Override
    public CommonResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        return iCommonResponseMapper.toResponse(iRestaurantServicePort.saveRestaurant(iRestaurantRequestMapper.toRestaurantModel(restaurantRequestDto)));
    }

    @Override
    public List<RestaurantPageableResponseDto> getAllRestaurants(Pageable pageable) {
        return iRestaurantResponseMapper.toResponseListPageable( iRestaurantServicePort.getAllRestaurants(pageable) );
    }

}