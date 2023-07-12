package com.pragma.boulevard_microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantModel {

    private Long id;

    private String name;

    private String direction;

    private Long idOwner;

    private String phone;

    private String urlLogo;

    private String nit;

}
