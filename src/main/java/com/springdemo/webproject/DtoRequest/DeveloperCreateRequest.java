package com.springdemo.webproject.DtoRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeveloperCreateRequest {
    private String name;
    private String phone;
    private String email;
    private Integer teamId;
}
