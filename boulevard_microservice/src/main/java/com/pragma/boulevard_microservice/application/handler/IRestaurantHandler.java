package com.pragma.boulevard_microservice.application.handler;

import com.pragma.boulevard_microservice.application.dto.request.RestaurantRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    public CommonResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    public List<RestaurantResponseDto> getAllRestaurants();

    public RestaurantResponseDto getRestaurant(Long restaurantId);

    public void updateRestaurant(RestaurantRequestDto restaurantRequestDto);

    public void deleteRestaurant(Long restaurantId);

}