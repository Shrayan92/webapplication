package com.springdemo.webproject;

import com.springdemo.webproject.DtoRequest.MessageRequest;
import com.springdemo.webproject.DtoRequest.NotificationRequest;
import com.springdemo.webproject.data.DeveloperData;
import com.springdemo.webproject.data.TeamData;
import com.springdemo.webproject.entity.Developer;
import com.springdemo.webproject.entity.Team;
import com.springdemo.webproject.util.StringConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest

@RunWith(SpringJUnit4ClassRunner.class)
public class TestWebApplicationNotification {
    @Autowired
    private TeamData teamData;
    @Autowired
    private DeveloperData developerData;

    @Test
    public void testNotification(){
        NotificationRequest notificationRequest = new NotificationRequest("team1","error in service");
        Team responsedata=teamData.findByName(notificationRequest.getTeam());
        List<Developer> developersList = developerData.findByTeamId(responsedata.getId());
        Developer developer = developersList.get(0);
        MessageRequest messageRequest = MessageRequest.builder().phone(developer.getPhone()).error(notificationRequest.getError()).build();
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<MessageRequest> entity = new HttpEntity<MessageRequest>(messageRequest,headers);
            RestTemplate restTemplate = new RestTemplate();
            String response= restTemplate.exchange(
                    "https://sample.notification.com/v1/esfeof-slkeof-sljfio-slwof", HttpMethod.POST, entity, String.class).getBody();
        }
        catch (Exception e){
            System.out.println(e);
        }
        Assert.assertEquals(StringConstants.MESSAGE_SENT,"SMS is sent to developer");


    }

}
