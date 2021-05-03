package com.springdemo.webproject.controller;

import com.springdemo.webproject.DtoRequest.NotificationRequest;
import com.springdemo.webproject.controller.DtoResponse.ApiResponse;
import com.springdemo.webproject.service.INotificationService;
import com.springdemo.webproject.util.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NotificationController {
    @Autowired
    private INotificationService iNotificationService;

    @PostMapping("/send/notification")
    public ResponseEntity<ApiResponse> sendNotification(@RequestBody NotificationRequest notificationRequest) throws Exception {
        log.info("Received Error Notification Request for team{}",notificationRequest.getTeam());
        String response = iNotificationService.sendNotification(notificationRequest);
        log.info("Received Response from Messaging Service {}",response);
        ApiResponse<String> apiResponse= new ApiResponse<>(StringConstants.SUCCESS_STATUS_CODE,response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
