package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.DatosTest;
import com.pragma.boulevard_microservice.domain.api.IOrderServicePort;
import com.pragma.boulevard_microservice.domain.exception.BadRequestException;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.*;
import com.pragma.boulevard_microservice.domain.spi.*;
import com.pragma.boulevard_microservice.infrastructure.exception.DishNotBelongRestaurantException;
import com.pragma.boulevard_microservice.infrastructure.exception.DishNotFoundException;
import com.pragma.boulevard_microservice.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderUseCaseTest {

    private IOrderPersistencePort iOrderPersistencePort;
    private IOrderDishPersistencePort iOrderDishPersistencePort;
    private IOrderServicePort iOrderServicePort;
    private OrderUseCase orderUseCase;
    private IEmployeePersistencePort iEmployeePersistencePort;

    private IUserPersistencePort iUserPersistencePort;
    private IMessagingPersistencePort iMessagingPersistencePort;

    @BeforeEach
    void setUp(){

        iOrderPersistencePort = mock(IOrderPersistencePort.class);
        iOrderDishPersistencePort = mock(IOrderDishPersistencePort.class);
        iEmployeePersistencePort = mock(IEmployeePersistencePort.class);
        iOrderServicePort = mock(IOrderServicePort.class);
        iUserPersistencePort = mock(IUserPersistencePort.class);
        iMessagingPersistencePort = mock(IMessagingPersistencePort.class);

        orderUseCase = new OrderUseCase(iOrderPersistencePort, iOrderDishPersistencePort, iEmployeePersistencePort,
                iUserPersistencePort, iMessagingPersistencePort);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void saveOrderTest() {
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(4L);
        OrderModel order = new OrderModel();
        order.setId(5L);
        order.setRestaurantModel(restaurant);

        OrderDishModel orderDish = new OrderDishModel();
        DishModel dish = new DishModel();
        dish.setId(999L);
        orderDish.setDishModel(dish);
        orderDish.setQuantity(2);

        List<OrderDishModel> dishList = new ArrayList<>();
        dishList.add(orderDish);

        Long idClient = 1L;

        when(iOrderPersistencePort.findOrderInProcessByClient(idClient)).thenReturn(Collections.emptyList());
        when(iOrderPersistencePort.saveOrder(order)).thenReturn(order);

        assertNotNull(orderUseCase.saveOrder(order, dishList));
    }

    @Test
    void saveOrderDishNotBelongRestaurantExceptionTest() {
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(4L);
        OrderModel order = new OrderModel();
        order.setId(5L);
        order.setRestaurantModel(restaurant);

        OrderDishModel orderDish = new OrderDishModel();
        DishModel dish = new DishModel();
        dish.setId(999L);
        orderDish.setDishModel(dish);
        orderDish.setQuantity(2);

        List<OrderDishModel> dishList = new ArrayList<>();
        dishList.add(orderDish);

        Long idClient = 1L;

        when(iOrderPersistencePort.findOrderInProcessByClient(idClient)).thenReturn(Collections.emptyList());
        when(iOrderPersistencePort.saveOrder(order)).thenReturn(order);

        doThrow(DishNotBelongRestaurantException.class).when(iOrderDishPersistencePort).saveOrderDish(orderDish, order.getRestaurantModel().getId());
        assertThrows(DishNotBelongRestaurantException.class, () -> orderUseCase.saveOrder(order, dishList));
    }

    @Test
    void saveOrderDishNotFoundExceptionTest() {
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(4L);
        OrderModel order = new OrderModel();
        order.setRestaurantModel(restaurant);

        OrderDishModel orderDish = new OrderDishModel();
        DishModel dish = new DishModel();
        dish.setId(999L);
        orderDish.setDishModel(dish);
        orderDish.setQuantity(2);

        List<OrderDishModel> dishList = new ArrayList<>();
        dishList.add(orderDish);

        Long idClient = 1L;

        when(iOrderPersistencePort.findOrderInProcessByClient(idClient)).thenReturn(Collections.emptyList());
        when(iOrderPersistencePort.saveOrder(order)).thenReturn(order);

        doThrow(DishNotFoundException.class).when(iOrderDishPersistencePort).saveOrderDish(orderDish, order.getRestaurantModel().getId());
        assertThrows(DishNotFoundException.class, () -> orderUseCase.saveOrder(order, dishList));
    }

    @Test
    void saveOrderDomainExceptionTest() {
        Long idClient = 3L;

        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(1L);
        OrderModel order = new OrderModel();
        order.setId(4L);
        order.setRestaurantModel(restaurant);
        order.setIdClient(idClient);

        OrderDishModel orderDish = new OrderDishModel();
        DishModel dish = new DishModel();
        dish.setId(1L);
        orderDish.setDishModel(dish);
        orderDish.setQuantity(3);

        List<OrderDishModel> dishList = new ArrayList<>();
        dishList.add(orderDish);

        List<OrderModel> orderList = new ArrayList<>();
        orderList.add(order);

        when(iOrderPersistencePort.findOrderInProcessByClient(idClient)).thenReturn(orderList);
        //when(iOrderPersistencePort.saveOrder(order)).thenReturn(order);
        when(iOrderServicePort.saveOrder(order, dishList)).thenThrow(DomainException.class);
        assertThrows(DomainException.class, () -> orderUseCase.saveOrder(order, dishList));
    }

    @Test
    void getOrderByStatusTest() {
        Pageable pageable = PageRequest.of( 0, 10 );

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);

        EmployeeModel employeeModel = new EmployeeModel(1L, 6L, restaurantModel);

        when(iEmployeePersistencePort.findByIdEmployee(6L)).thenReturn(employeeModel);
        assertNotNull(orderUseCase.getOrderByStatus("Pending", 6L, pageable));
    }

    @Test
    void getOrderByStatusNoDataFoundExceptionTest() {
        Pageable pageable = PageRequest.of( 0, 10 );

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(2L);

        EmployeeModel employeeModel = new EmployeeModel(1L, 7L, restaurantModel);

        when(iEmployeePersistencePort.findByIdEmployee(6L)).thenReturn(employeeModel);
        when(iOrderPersistencePort.getOrderByStatus(2L, "Delivered", pageable)).thenThrow(NoDataFoundException.class);
        assertThrows(NoDataFoundException.class, () -> orderUseCase.getOrderByStatus("Delivered", 6L, pageable));
    }

    @Test
    void assignToOrderSuccessfulTest() {
        when(iEmployeePersistencePort.findByIdEmployee(7L)).thenReturn(DatosTest.EMPLOYEE_001);
        when(iOrderPersistencePort.getOrder(1L)).thenReturn(DatosTest.ORDER_MODEL_001);
        when(iOrderPersistencePort.saveOrder(DatosTest.ORDER_MODEL_001)).thenReturn(DatosTest.ORDER_MODEL_001);

        assertNotNull(orderUseCase.assignToOrder(DatosTest.ORDER_MODEL_LIST_001, 7L));
    }

    @Test
    void assignToOrderEmptyListExceptionTest() {
        assertThrows(DomainException.class, () -> orderUseCase.assignToOrder(new ArrayList<>(), 7L));
    }

    @Test
    void assignToOrderEmployeeNotFoundExceptionTest() {
        assertThrows(DomainException.class, () -> orderUseCase.assignToOrder(DatosTest.ORDER_MODEL_LIST_001, 7L));
    }

    @Test
    void assignToOrderNotBelongRestaurantExceptionTest() {
        when(iEmployeePersistencePort.findByIdEmployee(7L)).thenReturn(DatosTest.EMPLOYEE_001);
        when(iOrderPersistencePort.getOrder(2L)).thenReturn(DatosTest.ORDER_MODEL_002);

        assertThrows(DomainException.class, () -> orderUseCase.assignToOrder(DatosTest.ORDER_MODEL_LIST_002, 7L));
    }

    @Test
    void orderReadyTest() {
        CommonResponseModel<UserModel> commonResponseModel = new CommonResponseModel<>(true, "200", "OK", DatosTest.USER_MODEL_001);

        when(iOrderPersistencePort.getOrder(2L)).thenReturn(DatosTest.ORDER_MODEL_002);
        when(iUserPersistencePort.findUserById(DatosTest.ORDER_MODEL_002.getIdClient())).thenReturn(commonResponseModel);
        when(iOrderPersistencePort.saveOrder(DatosTest.ORDER_MODEL_002)).thenReturn(DatosTest.ORDER_MODEL_002);
        when(iMessagingPersistencePort.sendCodeVerification("Your order is ready.", DatosTest.USER_MODEL_001.getPhone())).thenReturn(any(HashMap.class));

        assertNotNull(orderUseCase.orderReady(2L));
    }

    @Test
    void orderReadyBadRequestExceptionTest() {

        when(iOrderPersistencePort.getOrder(2L)).thenReturn(DatosTest.ORDER_MODEL_001);
        assertThrows(BadRequestException.class, () -> orderUseCase.orderReady(2L));

    }

}