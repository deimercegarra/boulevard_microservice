package com.pragma.boulevard_microservice.application.handler;

import com.pragma.boulevard_microservice.application.dto.request.OrderRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {

    public CommonResponseDto saveOrder(OrderRequestDto orderRequestDto);

    public List<OrderResponseDto> getAllOrders();

    public OrderResponseDto getOrder(Long orderId);

    public void updateOrder(OrderRequestDto orderRequestDto);

    public void deleteOrder(Long orderId);

}