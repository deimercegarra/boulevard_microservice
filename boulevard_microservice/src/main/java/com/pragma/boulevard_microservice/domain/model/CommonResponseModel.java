package com.pragma.boulevard_microservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommonResponseModel<E>{

    private String message;
    private String code;
    private E dto;
    private Long id;
    private Boolean status;

    public CommonResponseModel(Boolean status, String code, String message, E dto, Long id) {
        this.message = message;
        this.dto = dto;
        this.id = id;
        this.status = status;
        this.code = code;
    }

    public CommonResponseModel(Boolean status, String code, String message, E dto) {
        this.message = message;
        this.dto = dto;
        this.status = status;
        this.code = code;
    }

    public CommonResponseModel(String code, String message, Boolean status) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", dto=" + dto +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
