package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.application.dto.request.SmsRequestDto;
import com.pragma.boulevard_microservice.application.dto.request.ValidateCodeRequestDto;
import com.pragma.boulevard_microservice.domain.spi.IMessagingPersistencePort;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.client.usermicroservice.TwilioClient;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MessagingJpaAdapter implements IMessagingPersistencePort {

    private final TwilioClient twilioClient;

    @Override
    public void notifyOrderStatus(String message, String phone) {
        SmsRequestDto smsRequestDto;
        smsRequestDto = new SmsRequestDto(message, phone);
        twilioClient.notifyOrderStatus(smsRequestDto);
    }

    @Override
    public Map<String, String> sendCodeVerification(String message, String phone) {
        SmsRequestDto smsRequestDto;
        smsRequestDto = new SmsRequestDto(message, phone);
        return twilioClient.sendCodeSms(smsRequestDto);
    }

    @Override
    public Map<String, String> validateCodeVerification(String code, String phone) {
        ValidateCodeRequestDto validateCodeRequestDto;
        validateCodeRequestDto = new ValidateCodeRequestDto(code, phone);
        return twilioClient.validateCodeSms(validateCodeRequestDto);
    }

}
