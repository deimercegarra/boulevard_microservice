package com.pragma.boulevard_microservice.domain.api;

import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;

public interface IUserServicePort {

    public CommonResponseModel findRole(Long userId);

}