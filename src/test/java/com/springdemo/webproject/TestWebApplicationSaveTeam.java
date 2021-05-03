package com.springdemo.webproject;

import com.springdemo.webproject.DtoRequest.DeveloperCreateRequest;
import com.springdemo.webproject.DtoRequest.TeamCreateRequest;
import com.springdemo.webproject.data.DeveloperData;
import com.springdemo.webproject.data.TeamData;
import com.springdemo.webproject.entity.Developer;
import com.springdemo.webproject.entity.Team;
import com.springdemo.webproject.util.StringConstants;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWebApplicationSaveTeam {
    @Autowired
    private TeamData teamData;
    @Autowired
    private DeveloperData developerData;
    @Transactional
    public void saveTeamTest() throws Exception {
        List<DeveloperCreateRequest> developerCreateRequestList = new ArrayList<>();
        developerCreateRequestList.add(new DeveloperCreateRequest("developer1","123456","abc@qrs.com",1));
        TeamCreateRequest teamCreateRequest = new TeamCreateRequest("team2",developerCreateRequestList);
        Team team = Team.builder().name(teamCreateRequest.getName()).build();
        Team responseTeam= teamData.save(team);
        saveDeveloperList(teamCreateRequest.getDeveloperCreateRequestList(), responseTeam.getId());
        Assert.assertEquals(StringConstants.TEAM_CREATED,"Team is created");
    }


    @Transactional
    public void saveDeveloperList(List<DeveloperCreateRequest> developerCreateRequestList, Integer teamId) throws Exception {
        List<Developer> developersList = new ArrayList<>();
        for(DeveloperCreateRequest developerCreateRequest:developerCreateRequestList){
            developersList.add(Developer.builder().name(developerCreateRequest.getName())
                    .phone(developerCreateRequest.getPhone()).email(developerCreateRequest.getEmail()).teamId(teamId).build());
        }
    }
}
