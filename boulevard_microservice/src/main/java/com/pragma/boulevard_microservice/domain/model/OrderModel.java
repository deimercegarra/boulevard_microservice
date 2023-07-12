package com.pragma.boulevard_microservice.domain.model;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {

    private Long id;

    private Long idOwner;

    private Date date_order;

    private String status;

    private Long idChef;

    private RestaurantEntity restaurantEntity;

}
