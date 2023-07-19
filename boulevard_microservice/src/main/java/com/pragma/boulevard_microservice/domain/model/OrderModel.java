package com.pragma.boulevard_microservice.domain.model;

import com.pragma.boulevard_microservice.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


public class OrderModel {

    private Long id;

    private Long idOwner;

    private Date date_order;

    private String status;

    private Long idChef;

    private RestaurantEntity restaurantEntity;

    public OrderModel(Long id, Long idOwner, Date date_order, String status, Long idChef, RestaurantEntity restaurantEntity) {
        this.id = id;
        this.idOwner = idOwner;
        this.date_order = date_order;
        this.status = status;
        this.idChef = idChef;
        this.restaurantEntity = restaurantEntity;
    }

    public OrderModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Date getDate_order() {
        return date_order;
    }

    public void setDate_order(Date date_order) {
        this.date_order = date_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }
}
