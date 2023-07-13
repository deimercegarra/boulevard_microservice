package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;

public interface IUserPersistencePort {

    public CommonResponseModel findRole(Long userId);

}