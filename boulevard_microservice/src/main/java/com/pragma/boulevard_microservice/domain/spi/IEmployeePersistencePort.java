package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.domain.model.EmployeeModel;
import com.pragma.boulevard_microservice.domain.model.OrderDishModel;

import java.util.List;

public interface IEmployeePersistencePort {
    EmployeeModel findByIdEmployee(Long employeeId);

}