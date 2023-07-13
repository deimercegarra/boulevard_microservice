package com.pragma.boulevard_microservice.infrastructure.input.rest;

import com.pragma.boulevard_microservice.application.dto.request.DishRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler iDishHandler;

    @Operation(summary = "Add a new Dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created.", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists.", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<CommonResponseDto> saveDish(@Valid  @RequestBody DishRequestDto DishRequestDto) {
        CommonResponseDto commonResponseDto = iDishHandler.saveDish(DishRequestDto);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponseDto);
    }

}