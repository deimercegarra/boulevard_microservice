package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderServicePort {

    CommonResponseModel saveOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList);

    OrderModel getOrder(Long orderId);

    void updateOrder(OrderModel orderModel);

    void deleteOrder(Long orderId);

    List<OrderModel> getOrderByStatus(String status, Long employeeId, Pageable pageable);

    List<OrderModel> assignToOrder(List<OrderModel> modelList, Long employeeId);

    CommonResponseModel orderReady(Long orderId);
}