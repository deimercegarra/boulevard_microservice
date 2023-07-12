package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.domain.spi.IDishPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.exception.NoDataFoundException;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;

    @Override
    public void saveDish(DishModel dishModel) {
        iDishRepository.save(iDishEntityMapper.toEntity(dishModel));
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> entityList = iDishRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iDishEntityMapper.toModelList(entityList);
    }

    @Override
    public DishModel getDish(Long userId) {
        return iDishEntityMapper.toModel(iDishRepository.findById(userId)
                .orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void updateDish(DishModel dishModel) {
        iDishRepository.save(iDishEntityMapper.toEntity(dishModel));
    }

    @Override
    public void deleteDish(Long userId) {
        iDishRepository.deleteById(userId);
    }

}