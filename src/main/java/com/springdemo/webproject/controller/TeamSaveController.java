package com.springdemo.webproject.controller;

import com.springdemo.webproject.DtoRequest.TeamCreateRequest;
import com.springdemo.webproject.controller.DtoResponse.ApiResponse;
import com.springdemo.webproject.service.ITeamSaveService;
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
public class TeamSaveController {

    @Autowired
    private ITeamSaveService iTeamSaveService;

    @PostMapping("/save/team")
    public ResponseEntity<ApiResponse> saveTeam(@RequestBody TeamCreateRequest teamCreateRequest) throws Exception {
        log.info("Received request for creating team");
        String teamCreated = iTeamSaveService.saveTeam(teamCreateRequest);
        log.info("Team Created");
        ApiResponse<String> apiResponse= new ApiResponse<>(StringConstants.SUCCESS_STATUS_CODE,teamCreated);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
