package com.pragma.boulevard_microservice.application.dto.request;


import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderAssignRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long id;

}