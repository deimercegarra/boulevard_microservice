package com.pragma.boulevard_microservice.infrastructure.out.jpa.repository;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

}