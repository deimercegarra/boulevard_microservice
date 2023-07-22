package com.pragma.boulevard_microservice.application.dto.response;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;

    private Long idClient;

    private Date dateOrder;

    private String statusOrder;

    private Long idChef;

    private Long idRestaurant;

}
