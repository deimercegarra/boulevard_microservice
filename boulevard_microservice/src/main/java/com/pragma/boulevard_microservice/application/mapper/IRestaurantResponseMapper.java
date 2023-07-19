package com.pragma.boulevard_microservice.application.mapper;

import com.pragma.boulevard_microservice.application.dto.response.RestaurantPageableResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.RestaurantResponseDto;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponseDto toResponse(RestaurantModel restaurantModel);

    List<RestaurantResponseDto> toResponseList(List<RestaurantModel>  restaurantModelList);

    List<RestaurantPageableResponseDto> toResponseListPageable(List<RestaurantModel> allRestaurants);
}
