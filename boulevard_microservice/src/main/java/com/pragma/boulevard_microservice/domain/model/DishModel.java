package com.pragma.boulevard_microservice.domain.model;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishModel {

    private Long id;

    private String name;

    private CategoryEntity categoryEntity;

    private String description;

    private String price;

    private RestaurantEntity restaurantEntity;

    private String urlImage;

    private boolean active;

}
