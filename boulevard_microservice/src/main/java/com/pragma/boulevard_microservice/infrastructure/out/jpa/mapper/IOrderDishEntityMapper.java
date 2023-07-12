package com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderDishEntityMapper {

    OrderDishEntity toEntity(OrderDishModel orderDishModel);
    OrderDishModel toModel(OrderDishEntity orderDishEntity);
    List<OrderDishModel> toModelList(List<OrderDishEntity> orderDishEntities);

}