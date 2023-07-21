package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.domain.api.IOrderServicePort;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IOrderPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;

import java.util.Date;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort iOrderPersistencePort;
    private final IOrderDishPersistencePort iOrderDishPersistencePort;

    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort,
                        IOrderDishPersistencePort iOrderDishPersistencePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
        this.iOrderDishPersistencePort = iOrderDishPersistencePort;
    }

    @Override
    public CommonResponseModel saveOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList) {

        List<OrderModel> orderList = iOrderPersistencePort.findOrderInProcessByClient(  orderModel.getIdClient() ) ;

        if (!orderList.isEmpty()) {
            throw new DomainException("Order in process.");
        }

        orderModel.setDateOrder(new Date());
        orderModel.setStatusOrder(Constants.ORDER_STATUS_PENDING);

        orderModel = iOrderPersistencePort.saveOrder(orderModel);

        for ( OrderDishModel orderDishModel: orderDishModelList ) {
            orderDishModel.setOrderModel(orderModel);
            iOrderDishPersistencePort.saveOrderDish(orderDishModel, orderModel.getRestaurantModel().getId());
        }

        return new CommonResponseModel("201","CREATED.", true);
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