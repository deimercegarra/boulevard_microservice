package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.api.IRestaurantServicePort;
import com.pragma.boulevard_microservice.domain.api.IUserServicePort;
import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.model.RoleModel;
import com.pragma.boulevard_microservice.domain.model.UserModel;
import com.pragma.boulevard_microservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class RestaurantUseCaseTest {

    private IRestaurantPersistencePort iRestaurantPersistencePort;
    private IRestaurantServicePort iRestaurantServicePort;
    private IUserServicePort iUserServicePort;
    private RestaurantUseCase restaurantUseCase;

    private UserModel userModel;
    private RestaurantModel restaurantModel;

    @BeforeEach
    void setUp(){

        iRestaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        iRestaurantServicePort = mock(IRestaurantServicePort.class);
        iUserServicePort = mock(IUserServicePort.class);
        restaurantUseCase = new RestaurantUseCase(iRestaurantPersistencePort, iUserServicePort);
        MockitoAnnotations.initMocks(this);

        restaurantModel = new RestaurantModel();

        restaurantModel.setName("lanota");
        restaurantModel.setDirection("turbay ayala");
        restaurantModel.setIdOwner(1L);
        restaurantModel.setPhone("31232");
        restaurantModel.setUrlLogo("fdhgfghfgh/xfnbfgh");
        restaurantModel.setNit("4545453243");
    }

    @Test
    void saveRestaurantTest() {
        CommonResponseModel commonResponseModel = new CommonResponseModel("201", "CREATED.", true);
        when(iUserServicePort.findRole(1L)).thenReturn(commonResponseModel);
        when(iRestaurantPersistencePort.saveRestaurant(restaurantModel)).thenReturn(restaurantModel);
        assertNotNull(restaurantUseCase.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurantNotSuccessfulTest() {
        CommonResponseModel commonResponseModel = new CommonResponseModel("404", "User not found.", false);
        when(iUserServicePort.findRole(1L)).thenReturn(commonResponseModel);
        when(iRestaurantPersistencePort.saveRestaurant(any(RestaurantModel.class))).thenReturn(restaurantModel);
        assertNotNull(restaurantUseCase.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurantInternalErrorDomainExceptionTest() {
        doThrow(DomainException.class).when(iUserServicePort).findRole(anyLong());
        assertThrows(DomainException.class, () -> restaurantUseCase.saveRestaurant(restaurantModel));
    }

}