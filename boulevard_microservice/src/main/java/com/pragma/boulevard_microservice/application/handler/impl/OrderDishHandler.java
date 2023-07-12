package com.pragma.boulevard_microservice.application.handler.impl;

import com.pragma.boulevard_microservice.application.dto.request.OrderDishRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.OrderDishResponseDto;
import com.pragma.boulevard_microservice.application.handler.IOrderDishHandler;
import com.pragma.boulevard_microservice.application.mapper.IOrderDishRequestMapper;
import com.pragma.boulevard_microservice.application.mapper.IOrderDishResponseMapper;
import com.pragma.boulevard_microservice.domain.api.IOrderDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDishHandler implements IOrderDishHandler {

    private final IOrderDishServicePort iOrderDishServicePort;
    private final IOrderDishRequestMapper iOrderDishRequestMapper;
    private final IOrderDishResponseMapper iOrderDishResponseMapper;

    @Override
    public void saveOrderDish(OrderDishRequestDto orderDishRequestDto) {
        iOrderDishServicePort.saveOrderDish(iOrderDishRequestMapper.toOrderDishModel(orderDishRequestDto));
    }

    @Override
    public List<OrderDishResponseDto> getAllOrderDish() {
        return iOrderDishResponseMapper.toResponseList(iOrderDishServicePort.getAllOrderDish());
    }

    @Override
    public OrderDishResponseDto getOrderDish(Long orderDishId) {
        return iOrderDishResponseMapper.toResponse(iOrderDishServicePort.getOrderDish(orderDishId));
    }

    @Override
    public void updateOrderDish(OrderDishRequestDto orderDishRequestDto) {
        iOrderDishServicePort.saveOrderDish(iOrderDishRequestMapper.toOrderDishModel(orderDishRequestDto));
    }

    @Override
    public void deleteOrderDish(Long orderDishId) {
        iOrderDishServicePort.deleteOrderDish(orderDishId);
    }
}