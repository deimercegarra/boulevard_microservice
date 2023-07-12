package com.pragma.boulevard_microservice.application.mapper;

import com.pragma.boulevard_microservice.application.dto.response.OrderDishResponseDto;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishResponseMapper {
    OrderDishResponseDto toResponse(OrderDishModel orderDishModel);

    List<OrderDishResponseDto> toResponseList(List<OrderDishModel> orderDishModelList);
}
