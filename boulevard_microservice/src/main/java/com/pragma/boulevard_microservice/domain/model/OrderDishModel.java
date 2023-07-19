package com.pragma.boulevard_microservice.domain.model;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class OrderDishModel {

    private OrderEntity orderEntity;

    private DishEntity dishEntity;

    private int quantity;

    public OrderDishModel(OrderEntity orderEntity, DishEntity dishEntity, int quantity) {
        this.orderEntity = orderEntity;
        this.dishEntity = dishEntity;
        this.quantity = quantity;
    }

    public OrderDishModel() {
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public DishEntity getDishEntity() {
        return dishEntity;
    }

    public void setDishEntity(DishEntity dishEntity) {
        this.dishEntity = dishEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
