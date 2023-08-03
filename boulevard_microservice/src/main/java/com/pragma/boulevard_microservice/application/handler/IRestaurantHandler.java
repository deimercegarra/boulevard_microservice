package com.pragma.boulevard_microservice.application.handler;

import com.pragma.boulevard_microservice.application.dto.request.RestaurantRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantPageableResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantHandler {

    public CommonResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    public List<RestaurantPageableResponseDto> getAllRestaurants(Pageable pageable);

}