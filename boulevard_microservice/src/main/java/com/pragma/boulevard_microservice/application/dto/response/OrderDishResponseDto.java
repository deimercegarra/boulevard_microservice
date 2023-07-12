package com.pragma.boulevard_microservice.application.dto.response;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishResponseDto {

    private OrderEntity orderEntity;

    private DishEntity dishEntity;

    private int quantity;

}
