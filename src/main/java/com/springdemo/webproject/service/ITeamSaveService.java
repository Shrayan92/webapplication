package com.springdemo.webproject.service;


import com.springdemo.webproject.DtoRequest.DeveloperCreateRequest;
import com.springdemo.webproject.DtoRequest.TeamCreateRequest;
import java.util.List;

public interface ITeamSaveService {
    public String saveTeam(TeamCreateRequest teamCreateRequest) throws Exception;
    public void saveDeveloperList(List<DeveloperCreateRequest> developerCreateRequest,Integer teamId) throws Exception;
}
