package com.pragma.boulevard_microservice.infrastructure.out.jpa.repository;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findByIdEmployee(Long idEmployee);


}