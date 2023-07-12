package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.domain.api.IRestaurantServicePort;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IUserPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IUserPersistencePort iUserPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort iRestaurantPersistencePort,
                             IUserPersistencePort iUserPersistencePort) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iUserPersistencePort = iUserPersistencePort;
    }

    @Override
    public CommonResponseModel saveRestaurant(RestaurantModel restaurantModel) {
        CommonResponseModel commonResponseModel = new CommonResponseModel();

        try {
            commonResponseModel = iUserPersistencePort.findOwner(restaurantModel.getIdOwner());
        } catch (Exception ex) {
            throw new DomainException(new CommonResponseDto("500","Internal error.", false).toString());
        }

        if (!commonResponseModel.getStatus()) {
            return new CommonResponseModel(commonResponseModel.getCode(),commonResponseModel.getMessage(), false);
        }

        iRestaurantPersistencePort.saveRestaurant(restaurantModel);

        return new CommonResponseModel("201","CREATED.", true);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return iRestaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public RestaurantModel getRestaurant(Long restaurantId) {
        return iRestaurantPersistencePort.getRestaurant(restaurantId);
    }

    @Override
    public void updateRestaurant(RestaurantModel restaurantModel) {
        iRestaurantPersistencePort.updateRestaurant(restaurantModel);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        iRestaurantPersistencePort.deleteRestaurant(restaurantId);
    }
}