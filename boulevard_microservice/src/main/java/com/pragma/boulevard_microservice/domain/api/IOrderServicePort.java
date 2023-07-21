package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.domain.model.OrderModel;

import java.util.List;

public interface IOrderServicePort {

    CommonResponseModel saveOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList);

    List<OrderModel> getAllOrders();

    OrderModel getOrder(Long orderId);

    void updateOrder(OrderModel orderModel);

    void deleteOrder(Long orderId);

}