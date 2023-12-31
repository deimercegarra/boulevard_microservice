package com.pragma.boulevard_microservice.infrastructure.out.jpa.client.usermicroservice;

import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${user_microservice.name}", url = "${user_microservice.url}")
public interface UserClient {

    @GetMapping("/api/v1/user/findRole/{userId}")
    public CommonResponseDto findRole(@PathVariable Long userId);

}
