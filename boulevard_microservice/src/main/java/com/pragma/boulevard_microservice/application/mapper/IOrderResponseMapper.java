package com.pragma.boulevard_microservice.application.mapper;

import com.pragma.boulevard_microservice.application.dto.response.OrderResponseDto;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {
    OrderResponseDto toResponse(OrderModel orderModel);

    List<OrderResponseDto> toResponseList(List<OrderModel> orderModelList);

}
