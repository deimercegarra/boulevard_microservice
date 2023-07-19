package com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper;


import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IDishEntityMapper {

    DishEntity toEntity(DishModel dishModel);
    DishModel toModel(DishEntity dishEntity);
    List<DishModel> toModelList(List<DishEntity> dishEntityList);
}