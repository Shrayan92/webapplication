package com.springdemo.webproject.controller.DtoResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResponse<E> implements Serializable {
    @JsonProperty("statuscode")
    private Integer statusCode;
    private E data;
}
