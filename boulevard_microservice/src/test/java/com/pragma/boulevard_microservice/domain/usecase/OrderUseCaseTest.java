package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IOrderPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.exception.DishNotBelongRestaurantException;
import com.pragma.boulevard_microservice.infrastructure.exception.DishNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderUseCaseTest {

    private IOrderPersistencePort iOrderPersistencePort;
    private IOrderDishPersistencePort iOrderDishPersistencePort;
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp(){

        iOrderPersistencePort = mock(IOrderPersistencePort.class);
        iOrderDishPersistencePort = mock(IOrderDishPersistencePort.class);

        orderUseCase = new OrderUseCase(iOrderPersistencePort, iOrderDishPersistencePort);
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
    void saveOrderDomainExceptionTest() {/*
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(1L);
        OrderModel order = new OrderModel();
        order.setId(4L);
        order.setRestaurantModel(restaurant);

        OrderDishModel orderDish = new OrderDishModel();
        DishModel dish = new DishModel();
        dish.setId(1L);
        orderDish.setDishModel(dish);
        orderDish.setQuantity(3);

        List<OrderDishModel> dishList = new ArrayList<>();
        dishList.add(orderDish);

        Long idClient = 3L;

        List<OrderModel> orderList = new ArrayList<>();
        orderList.add(order);

        when(iOrderPersistencePort.findOrderInProcessByClient(idClient)).thenReturn(orderList);
        assertThrows(DomainException.class, () -> orderUseCase.saveOrder(order, dishList));*/
    }

}