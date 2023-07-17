package com.pragma.boulevard_microservice.domain.usecase;

import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.domain.model.CategoryModel;
import com.pragma.boulevard_microservice.domain.model.DishModel;
import com.pragma.boulevard_microservice.domain.model.RestaurantModel;
import com.pragma.boulevard_microservice.domain.spi.IDishPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.boulevard_microservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    private IDishPersistencePort iDishPersistencePort;
    private DishUseCase dishUseCase;

    private IRestaurantPersistencePort iRestaurantPersistencePort;
    private IUserPersistencePort iUserPersistencePort;

    private DishModel dishModel;
    private CategoryModel categoryModel;

    private RestaurantModel restaurantModel;

    @BeforeEach
    void setUp(){

        iDishPersistencePort = mock(IDishPersistencePort.class);
        iRestaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        iUserPersistencePort = mock(IUserPersistencePort.class);
        dishUseCase = new DishUseCase(iDishPersistencePort, iRestaurantPersistencePort, iUserPersistencePort);
        MockitoAnnotations.initMocks(this);

        dishModel = new DishModel();

        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(2L);
    }

    @Test
    void saveDish() {
        restaurantModel = new RestaurantModel();

        restaurantModel.setId(11L);
        restaurantModel.setName("lanota");
        restaurantModel.setDirection("turbay ayala");
        restaurantModel.setIdOwner(2L);
        restaurantModel.setPhone("31232");
        restaurantModel.setUrlLogo("fdhgfghfgh/xfnbfgh");
        restaurantModel.setNit("4545453243");

        when(iRestaurantPersistencePort.getRestaurant(restaurantModel.getId())).thenReturn(restaurantModel);
        when(iDishPersistencePort.saveDish(dishModel)).thenReturn(dishModel);
        assertNotNull(dishUseCase.saveDish(dishModel));
    }

    @Test
    void saveDishRestaurantNotFoundDomainExceptionTest() {
        doThrow(DomainException.class).when(iRestaurantPersistencePort).getRestaurant(anyLong());
        assertThrows(DomainException.class, () -> dishUseCase.saveDish(dishModel));
    }

    @Test
    void saveDishUnauthorizedUserDomainExceptionTest() {
        dishModel = new DishModel();

        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(1L);

        doThrow(DomainException.class).when(iRestaurantPersistencePort).getRestaurant(dishModel.getRestaurantId());
        assertThrows(DomainException.class, () -> dishUseCase.saveDish(dishModel));
    }

    @Test
    void updateDishTest() {
        dishModel = new DishModel();

        dishModel.setId(2L);
        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(2L);

        when(iDishPersistencePort.getDish(dishModel.getId())).thenReturn(dishModel);
        when(iDishPersistencePort.updateDish(dishModel)).thenReturn(dishModel);
        assertNotNull(dishUseCase.updateDish(dishModel));
    }

    @Test
    void updateDishDishNotFoundDomainExceptionTest() {
        dishModel = new DishModel();

        dishModel.setId(0L);
        dishModel.setName("ceviche");
        dishModel.setCategoryId(1L);
        dishModel.setDescription("prueba");
        dishModel.setPrice(123564);
        dishModel.setRestaurantId(11L);
        dishModel.setUrlImage("dfgf/dfgfh");
        dishModel.setActive(true);
        dishModel.setUserId(4L);

        doThrow(DomainException.class).when(iDishPersistencePort).getDish(dishModel.getId());
        assertThrows(DomainException.class, () -> dishUseCase.updateDish(dishModel));
    }

}