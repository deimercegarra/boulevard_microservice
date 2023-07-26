package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.application.mapper.ICommonResponseMapper;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.UserModel;
import com.pragma.boulevard_microservice.domain.spi.IUserPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.client.usermicroservice.UserClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final UserClient userClient;
    private final ICommonResponseMapper iCommonResponseMapper;

    @Override
    public CommonResponseModel  findRole(Long userId) {
        return iCommonResponseMapper.toModel(userClient.findRole(userId));
    }

    @Override
    public CommonResponseModel<UserModel> findUserById(Long userId) {
        return iCommonResponseMapper.toModel(userClient.findUser(userId));
    }

}