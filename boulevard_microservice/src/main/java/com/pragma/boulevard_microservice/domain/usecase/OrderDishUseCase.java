package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.IOrderDishServicePort;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.domain.spi.IOrderDishPersistencePort;

import java.util.List;

public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort iOrderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort iOrderDishPersistencePort) {
        this.iOrderDishPersistencePort = iOrderDishPersistencePort;
    }

    @Override
    public void saveOrderDish(OrderDishModel orderDishModel) {
        iOrderDishPersistencePort.saveOrderDish(orderDishModel, null);
    }

    @Override
    public List<OrderDishModel> getAllOrderDish() {
        return iOrderDishPersistencePort.getAllOrderDish();
    }

    @Override
    public OrderDishModel getOrderDish(Long orderDishId) {
        return iOrderDishPersistencePort.getOrderDish(orderDishId);
    }

    @Override
    public void updateOrderDish(OrderDishModel orderDishModel) {
        iOrderDishPersistencePort.updateOrderDish(orderDishModel);
    }

    @Override
    public void deleteOrderDish(Long orderDishId) {
        iOrderDishPersistencePort.deleteOrderDish(orderDishId);
    }
}