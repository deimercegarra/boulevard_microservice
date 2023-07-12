package com.pragma.boulevard_microservice.application.mapper;

import com.pragma.boulevard_microservice.application.dto.request.CategoryRequestDto;
import com.pragma.boulevard_microservice.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    CategoryModel toCategoryModel(CategoryRequestDto userRequestDto);

}
