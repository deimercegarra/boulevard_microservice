package com.pragma.boulevard_microservice.domain.spi;

import com.pragma.boulevard_microservice.application.dto.request.SmsRequestDto;
import com.pragma.boulevard_microservice.application.dto.request.ValidateCodeRequestDto;
import com.pragma.boulevard_microservice.domain.model.CommonResponseModel;
import com.pragma.boulevard_microservice.domain.model.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IMessagingPersistencePort {

    void notifyOrderStatus(String message, String phone);

    Map<String, String> sendCodeVerification(String message, String phone);

    Map<String, String> validateCodeVerification(String code, String phone);

}