package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.domain.api.IRestaurantServicePort;
import com.pragma.boulevard_microservice.domain.api.IUserServicePort;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IUserPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IUserServicePort iUserServicePort;

    public RestaurantUseCase(IRestaurantPersistencePort iRestaurantPersistencePort,
                             IUserServicePort iUserServicePort) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iUserServicePort = iUserServicePort;
    }

    @Override
    public CommonResponseModel saveRestaurant(RestaurantModel restaurantModel) {
        CommonResponseModel commonResponseModel = new CommonResponseModel();

        try {
            commonResponseModel = iUserServicePort.findRole(restaurantModel.getIdOwner());
        } catch (Exception ex) {
            throw new DomainException(new CommonResponseDto("500","Internal error.", false).toString());
        }

        if (!commonResponseModel.getStatus()) {
            return new CommonResponseModel(commonResponseModel.getCode(),commonResponseModel.getMessage(), false);
        }

        if (!commonResponseModel.getMessage().equalsIgnoreCase(Constants.ROLE_ADMIN)) {
            return new CommonResponseModel("202","The role is not Administrator.", false);
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