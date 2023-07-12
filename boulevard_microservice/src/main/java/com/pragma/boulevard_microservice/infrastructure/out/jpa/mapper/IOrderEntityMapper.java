package com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderEntityMapper {

    OrderEntity toEntity(OrderModel orderModel);
    OrderModel toModel(OrderEntity orderEntity);
    List<OrderModel> toModelList(List<OrderEntity> orderEntityList);

}