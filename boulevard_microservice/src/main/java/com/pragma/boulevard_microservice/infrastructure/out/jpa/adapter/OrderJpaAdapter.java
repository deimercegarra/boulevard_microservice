package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.domain.spi.IOrderPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository iOrderRepository;
    private final IOrderEntityMapper iOrderEntityMapper;

    @Override
    public void saveOrder(OrderModel orderModel) {
        iOrderRepository.save(iOrderEntityMapper.toEntity(orderModel));
    }

    @Override
    public List<OrderModel> getAllOrders() {
        List<OrderEntity> entityList = iOrderRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iOrderEntityMapper.toModelList(entityList);
    }

    @Override
    public OrderModel getOrder(Long userId) {
        return iOrderEntityMapper.toModel(iOrderRepository.findById(userId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateOrder(OrderModel orderModel) {
        iOrderRepository.save(iOrderEntityMapper.toEntity(orderModel));
    }

    @Override
    public void deleteOrder(Long userId) {
        iOrderRepository.deleteById(userId);
    }

}