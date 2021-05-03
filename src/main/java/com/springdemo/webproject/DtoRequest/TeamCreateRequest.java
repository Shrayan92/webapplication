package com.springdemo.webproject.DtoRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TeamCreateRequest {
    private String name;
    private List<DeveloperCreateRequest> developerCreateRequestList;
}
