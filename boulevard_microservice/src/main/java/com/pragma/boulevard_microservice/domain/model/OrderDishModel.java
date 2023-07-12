package com.pragma.boulevard_microservice.domain.model;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishModel {

    private OrderEntity orderEntity;

    private DishEntity dishEntity;

    private int quantity;

}
