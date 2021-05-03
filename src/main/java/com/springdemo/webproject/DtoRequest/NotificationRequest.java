package com.springdemo.webproject.DtoRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequest {
    private String team;
    private String error;
}
