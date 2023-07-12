package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.IDishServicePort;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.domain.spi.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort iDishPersistencePort;

    public DishUseCase(IDishPersistencePort iDishPersistencePort) {
        this.iDishPersistencePort = iDishPersistencePort;
    }

    @Override
    public void saveDish(DishModel dishModel) {
        iDishPersistencePort.saveDish(dishModel);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return iDishPersistencePort.getAllDishes();
    }

    @Override
    public DishModel getDish(Long dishId) {
        return iDishPersistencePort.getDish(dishId);
    }

    @Override
    public void updateDish(DishModel dishModel) {
        iDishPersistencePort.updateDish(dishModel);
    }

    @Override
    public void deleteDish(Long dishId) {
        iDishPersistencePort.deleteDish(dishId);
    }
}