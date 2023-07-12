package com.pragma.boulevard_microservice.application.handler;

import com.pragma.boulevard_microservice.application.dto.request.OrderDishRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.OrderDishResponseDto;

import java.util.List;

public interface IOrderDishHandler {

    public void saveOrderDish(OrderDishRequestDto orderDishRequestDto);

    public List<OrderDishResponseDto> getAllOrderDish();

    public OrderDishResponseDto getOrderDish(Long orderDishId);

    public void updateOrderDish(OrderDishRequestDto orderDishRequestDto);

    public void deleteOrderDish(Long orderDishId);

}