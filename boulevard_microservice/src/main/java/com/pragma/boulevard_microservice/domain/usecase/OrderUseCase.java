package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.IOrderServicePort;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.EmployeeModel;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.domain.spi.IEmployeePersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IOrderPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort iOrderPersistencePort;
    private final IOrderDishPersistencePort iOrderDishPersistencePort;

    private final IEmployeePersistencePort iEmployeePersistencePort;

    public OrderUseCase(IOrderPersistencePort iOrderPersistencePort,
                        IOrderDishPersistencePort iOrderDishPersistencePort,
                        IEmployeePersistencePort iEmployeePersistencePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
        this.iOrderDishPersistencePort = iOrderDishPersistencePort;
        this.iEmployeePersistencePort = iEmployeePersistencePort;
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

    @Override
    public List<OrderModel> getOrderByStatus(String status, Long employeeId, Pageable pageable) {

        EmployeeModel employeeModel = iEmployeePersistencePort.findByIdEmployee( employeeId );
        Long idRestaurant = employeeModel.getRestaurantModel().getId();

        return iOrderPersistencePort.getOrderByStatus(idRestaurant, status, pageable);
    }

    @Override
    public List<OrderModel> assignToOrder(List<OrderModel> modelList, Long employeeId) {

        if (modelList.isEmpty()) {
            throw new DomainException("The sent list is empty.");
        }

        EmployeeModel employeeModel = iEmployeePersistencePort.findByIdEmployee(employeeId);

        if (employeeModel == null){
            throw new DomainException("An employee with the submitted id was not found.");
        }

        List<OrderModel> orderListUpdated = new ArrayList<>();

        for ( OrderModel order: modelList ) {

            order = iOrderPersistencePort.getOrder(order.getId());

            if (! (order.getRestaurantModel().getId().equals(employeeModel.getRestaurantModel().getId()))){
                throw new DomainException("One of the orders does not belong to the employee's restaurant.");
            }

            order.setIdChef(employeeModel.getId());
            order.setStatusOrder(Constants.ORDER_STATUS_PREPARATION);
            order = iOrderPersistencePort.saveOrder(order);

            orderListUpdated.add(order);
        }

        return orderListUpdated;
    }

}