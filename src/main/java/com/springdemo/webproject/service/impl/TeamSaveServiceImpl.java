package com.springdemo.webproject.service.impl;

import com.springdemo.webproject.DtoRequest.DeveloperCreateRequest;
import com.springdemo.webproject.DtoRequest.TeamCreateRequest;
import com.springdemo.webproject.data.DeveloperData;
import com.springdemo.webproject.data.TeamData;
import com.springdemo.webproject.entity.Developer;
import com.springdemo.webproject.entity.Team;
import com.springdemo.webproject.service.ITeamSaveService;
import com.springdemo.webproject.util.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TeamSaveServiceImpl implements ITeamSaveService {

    @Autowired
    private TeamData teamData;
    @Autowired
    private DeveloperData developerData;
    @Override
    @Transactional
    public String saveTeam(TeamCreateRequest teamCreateRequest) throws Exception {
        log.info("Team Creation Request Received");
        Team teamAlreadyExist = teamData.findByName(teamCreateRequest.getName());
        if(teamAlreadyExist != null){
            log.error("Team exist for {}",teamCreateRequest.getName());
            throw new Exception(StringConstants.TEAM_EXIST);
        }
        Team team = Team.builder().name(teamCreateRequest.getName()).build();
        log.info("Saving team");
        try {
            Team responseTeam= teamData.save(team);
            log.info("Saving developers in database");
            saveDeveloperList(teamCreateRequest.getDeveloperCreateRequestList(), responseTeam.getId());
        } catch (Exception e) {
            log.error("Error while saving team");
            throw new Exception(StringConstants.TEAM_CREATE_FAILURE);
        }
        log.info("Team and Developers saved in database");
        return StringConstants.TEAM_CREATED;
    }

    @Override
    @Transactional
    public void saveDeveloperList(List<DeveloperCreateRequest> developerCreateRequestList,Integer teamId) throws Exception {
        List<Developer> developersList = new ArrayList<>();
        log.info("Creating developers list");
        for(DeveloperCreateRequest developerCreateRequest:developerCreateRequestList){
            developersList.add(Developer.builder().name(developerCreateRequest.getName())
                                .phone(developerCreateRequest.getPhone()).email(developerCreateRequest.getEmail()).teamId(teamId).build());
        }
        log.info("Saving developers list");
        try{
            developerData.saveAll(developersList);
            log.info("Developers List saved in database");
        }
        catch (Exception e) {
            log.error("Error while saving developers list");
            throw new Exception(StringConstants.DEVELOPERLIST_CREATE_FAILURE);
        }

    }
}
