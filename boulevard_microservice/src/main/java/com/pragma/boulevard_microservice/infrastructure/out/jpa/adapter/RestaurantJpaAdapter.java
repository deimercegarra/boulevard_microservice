package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository iRestaurantRepository;
    private final IRestaurantEntityMapper iRestaurantEntityMapper;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        iRestaurantRepository.save(iRestaurantEntityMapper.toEntity(restaurantModel));
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> entityList = iRestaurantRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iRestaurantEntityMapper.toModelList(entityList);
    }

    @Override
    public RestaurantModel getRestaurant(Long id) {
        return iRestaurantEntityMapper.toModel(iRestaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new));
    }

    @Override
    public void updateRestaurant(RestaurantModel restaurantModel) {
        iRestaurantRepository.save(iRestaurantEntityMapper.toEntity(restaurantModel));
    }

    @Override
    public void deleteRestaurant(Long id) {
        iRestaurantRepository.deleteById(id);
    }

}