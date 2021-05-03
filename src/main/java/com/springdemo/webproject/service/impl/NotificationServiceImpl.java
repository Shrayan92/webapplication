package com.springdemo.webproject.service.impl;

import com.springdemo.webproject.DtoRequest.MessageRequest;
import com.springdemo.webproject.DtoRequest.NotificationRequest;
import com.springdemo.webproject.data.DeveloperData;
import com.springdemo.webproject.data.TeamData;
import com.springdemo.webproject.entity.Developer;
import com.springdemo.webproject.entity.Team;
import com.springdemo.webproject.service.INotificationService;
import com.springdemo.webproject.util.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    TeamData teamData;
    @Autowired
    DeveloperData developerData;
    @Override
    public String sendNotification(NotificationRequest notificationRequest) throws Exception {
        log.info("Received notification request for team {}",notificationRequest.getTeam());
        Team team = teamData.findByName(notificationRequest.getTeam());
        if(team == null)
        {
            log.error("Team not present in database");
            throw new Exception(StringConstants.TEAM_FETCH_FAILURE);
        }
        log.info("Received team data from database");
        List<Developer> developersList = developerData.findByTeamId(team.getId());
        log.info("Developers List Fetched from Database {}",developersList);
        Developer developer = developersList.get(0);
        MessageRequest messageRequest = MessageRequest.builder().phone(developer.getPhone()).error(notificationRequest.getError()).build();
        log.info("Message Request Created {}",messageRequest);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<MessageRequest> entity = new HttpEntity<MessageRequest>(messageRequest,headers);
            log.info("Headers Created");
            RestTemplate restTemplate = new RestTemplate();
            String response= restTemplate.exchange(
                    "https://sample.notification.com/v1/esfeof-slkeof-sljfio-slwof", HttpMethod.POST, entity, String.class).getBody();
            log.info("Response Received from Messaging Service {}",response);
            return response;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return StringConstants.MESSAGE_SENT;
    }
}
