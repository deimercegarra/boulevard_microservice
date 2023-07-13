package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.IDishServicePort;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.exception.UserNotFoundException;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IDishPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IUserPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort iDishPersistencePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IUserPersistencePort iUserPersistencePort;

    public DishUseCase(IDishPersistencePort iDishPersistencePort,
                       IRestaurantPersistencePort iRestaurantPersistencePort,
                       IUserPersistencePort iUserPersistencePort) {
        this.iDishPersistencePort = iDishPersistencePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iUserPersistencePort = iUserPersistencePort;
    }

    @Override
    public CommonResponseModel saveDish(DishModel dishModel) {
        CommonResponseModel commonResponseModel = new CommonResponseModel();

        RestaurantModel restaurantModel = iRestaurantPersistencePort.getRestaurant(dishModel.getRestaurantId());

        if (restaurantModel == null)
            throw new DomainException("Restaurant not found.");

        if (! (dishModel.getUserId() == restaurantModel.getIdOwner()) )
            throw new DomainException("Unauthorized user.");

        iDishPersistencePort.saveDish(dishModel);

        return new CommonResponseModel("201","CREATED.", true);
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