package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.IOrderServicePort;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort iOrderPersistencePort;

    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
    }

    @Override
    public void saveOrder(OrderModel orderModel) {
        iOrderPersistencePort.saveOrder(orderModel);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return iOrderPersistencePort.getAllOrders();
    }

    @Override
    public OrderModel getOrder(Long orderId) {
        return iOrderPersistencePort.getOrder(orderId);
    }

    @Override
    public void updateOrder(OrderModel orderModel) {
        iOrderPersistencePort.updateOrder(orderModel);
    }

    @Override
    public void deleteOrder(Long orderId) {
        iOrderPersistencePort.deleteOrder(orderId);
    }
}