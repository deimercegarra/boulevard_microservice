package com.pragma.boulevard_microservice;

import com.pragma.boulevard_microservice.domain.model.EmployeeModel;
import com.pragma.boulevard_microservice.domain.model.OrderModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatosTest {

    public static final RestaurantModel RESTAURANT_MODEL_001 = new RestaurantModel(2L, "pepes", "turbay ayala", 1L, "3123259058", "fdhgfghfgh/xfnbfgh", "64465564");
    public static final RestaurantModel RESTAURANT_MODEL_002 = new RestaurantModel(3L, "pepes", "turbay ayala", 1L, "3123259058", "fdhgfghfgh/xfnbfgh", "64465564");

    public static final OrderModel ORDER_MODEL_001 = new OrderModel(1L, 2L, new Date(), "Pending", 2L, RESTAURANT_MODEL_001);
    public static final OrderModel ORDER_MODEL_002 = new OrderModel(2L, 2L, new Date(), "Pending", 2L, RESTAURANT_MODEL_002);
    public static final EmployeeModel EMPLOYEE_001 = new EmployeeModel(2L, 7L, RESTAURANT_MODEL_001);

   /* public static final List<OrderModel> ORDER_MODEL_LIST_001 = Arrays.asList(new OrderModel(1L, 2L, new Date(), "Pending", 2L, RESTAURANT_MODEL_001),
            new OrderModel(3L, 2L, new Date(), "Pending", 3L, RESTAURANT_MODEL_001));*/

    public static final List<OrderModel> ORDER_MODEL_LIST_001 = Arrays.asList(ORDER_MODEL_001);
    public static final List<OrderModel> ORDER_MODEL_LIST_002 = Arrays.asList(ORDER_MODEL_002);


}