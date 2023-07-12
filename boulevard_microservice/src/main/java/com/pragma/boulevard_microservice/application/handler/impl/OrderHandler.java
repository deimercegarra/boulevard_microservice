package com.pragma.boulevard_microservice.application.handler.impl;

import com.pragma.boulevard_microservice.application.dto.request.OrderRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.OrderResponseDto;
import com.pragma.boulevard_microservice.application.handler.IOrderHandler;
import com.pragma.boulevard_microservice.application.mapper.IOrderRequestMapper;
import com.pragma.boulevard_microservice.application.mapper.IOrderResponseMapper;
import com.pragma.boulevard_microservice.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort iOrderServicePort;
    private final IOrderRequestMapper iOrderRequestMapper;
    private final IOrderResponseMapper iOrderResponseMapper;

    @Override
    public void saveOrder(OrderRequestDto OrderRequestDto) {
        iOrderServicePort.saveOrder(iOrderRequestMapper.toOrderModel(OrderRequestDto));
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return iOrderResponseMapper.toResponseList(iOrderServicePort.getAllOrders());
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        return iOrderResponseMapper.toResponse(iOrderServicePort.getOrder(orderId));
    }

    @Override
    public void updateOrder(OrderRequestDto OrderRequestDto) {
        iOrderServicePort.saveOrder(iOrderRequestMapper.toOrderModel(OrderRequestDto));
    }

    @Override
    public void deleteOrder(Long orderId) {
        iOrderServicePort.deleteOrder(orderId);
    }
}