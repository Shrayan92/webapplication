package com.springdemo.webproject.service;

import com.springdemo.webproject.DtoRequest.NotificationRequest;

public interface INotificationService {
    public String sendNotification(NotificationRequest NotificationRequest) throws Exception;
}
