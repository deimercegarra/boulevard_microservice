package com.pragma.boulevard_microservice.domain.model;

import com.pragma.boulevard_microservice.infrastructure.configuration.Constants;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishModel {

    private String name;
    private Long categoryId;
    private String description;
    private int price;
    private Long restaurantId;
    private String urlImage;
    private boolean active;
    private Long userId;

}
