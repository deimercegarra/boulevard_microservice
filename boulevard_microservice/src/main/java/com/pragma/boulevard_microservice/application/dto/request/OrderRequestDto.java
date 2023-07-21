package com.pragma.boulevard_microservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long idRestaurant;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private Long idCustomer;

    @NotNull(message = Constants.FIELD_NOT_NULL)
    private List<OrderDishRequestDto> orderDishRequestDtoList;

}
