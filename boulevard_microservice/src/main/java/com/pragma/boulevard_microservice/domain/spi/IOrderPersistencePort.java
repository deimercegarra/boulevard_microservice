package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.domain.model.OrderModel;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(OrderModel orderModel);

    List<OrderModel> getAllOrders();

    OrderModel getOrder(Long orderId);

    void updateOrder(OrderModel orderModel);

    void deleteOrder(Long orderId);

}