package com.pragma.boulevard_microservice.infrastructure.input.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma.boulevard_microservice.application.dto.request.OrderRequestDto;
import com.pragma.boulevard_microservice.application.dto.response.CommonResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.DishResponseDto;
import com.pragma.boulevard_microservice.application.dto.response.OrderResponseDto;
import com.pragma.boulevard_microservice.application.handler.IOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler iOrderHandler;

    @Operation(summary = "Add a new order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found.", content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<CommonResponseDto> saveOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        CommonResponseDto commonResponseDto = iOrderHandler.saveOrder(orderRequestDto);

        if (!commonResponseDto.getStatus())
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(commonResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponseDto);

    }

    /*
    The employeeID is the id of the user with the employee role from the user table.
     */
    @Operation(summary = "Find all orders by status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "202", description = "Request accepted but unsuccessful.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found.", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getOrderByStatus(@RequestParam String status,
                                                                                   @RequestParam Long employeeId,
                                                                                   @RequestParam(defaultValue = "1") Integer page,
                                                                                   @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(
                iOrderHandler.getOrderByStatus( status, employeeId,
                        PageRequest.of( page-1, size)
                )
        );

    }

}