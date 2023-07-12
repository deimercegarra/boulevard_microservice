package com.pragma.boulevard_microservice.infrastructure.out.jpa.repository;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

}