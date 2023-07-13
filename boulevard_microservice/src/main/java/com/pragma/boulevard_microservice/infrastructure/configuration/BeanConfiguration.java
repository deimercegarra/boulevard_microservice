package com.pragma.boulevard_microservice.infrastructure.configuration;

import com.pragma.boulevard_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice.domain.api.*;
import com.pragma.boulevard_microservice.domain.spi.*;
import com.pragma.boulevard_microservice.domain.usecase.*;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter.*;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.client.usermicroservice.UserClient;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper.*;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;

    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;

    private final IOrderRepository iOrderRepository;
    private final IOrderEntityMapper iOrderEntityMapper;

    private final IOrderDishRepository iOrderDishRepository;
    private final IOrderDishEntityMapper iOrderDishEntityMapper;

    private final IRestaurantRepository iRestaurantRepository;
    private final IRestaurantEntityMapper iRestaurantEntityMapper;

    private final UserClient userClient;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Bean
    public ICategoryPersistencePort iCategoryPersistencePort() {
        return new CategoryJpaAdapter(iCategoryRepository, iCategoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(iCategoryPersistencePort());
    }

    @Bean
    public IDishPersistencePort iDishPersistencePort() {
        return new DishJpaAdapter(iDishRepository, iDishEntityMapper);
    }
    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(iDishPersistencePort());
    }

    @Bean
    public IOrderPersistencePort iOrderPersistencePort() {
        return new OrderJpaAdapter(iOrderRepository, iOrderEntityMapper);
    }
    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(iOrderPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort iOrderDishPersistencePort() {
        return new OrderDishJpaAdapter(iOrderDishRepository, iOrderDishEntityMapper);
    }
    @Bean
    public IOrderDishServicePort orderDishServicePort() {
        return new OrderDishUseCase(iOrderDishPersistencePort());
    }

    @Bean
    public IRestaurantPersistencePort iRestaurantPersistencePort() {
        return new RestaurantJpaAdapter(iRestaurantRepository, iRestaurantEntityMapper);
    }

    @Bean
    public IUserServicePort iUserServicePort() {
        return new UserUseCase(iUserPersistencePort());
    }

    @Bean
    public IUserPersistencePort iUserPersistencePort() {
        return new UserJpaAdapter(userClient, iCommonResponseMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(iRestaurantPersistencePort(), iUserServicePort());
    }

}