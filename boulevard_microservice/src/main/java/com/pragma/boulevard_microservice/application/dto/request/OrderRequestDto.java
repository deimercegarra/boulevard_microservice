package com.pragma.boulevard_microservice.application.dto.request;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderRequestDto {

    private Long idOwner;

    private Date date_order;

    private String status;

    private Long idChef;

    private RestaurantEntity restaurantEntity;

}
