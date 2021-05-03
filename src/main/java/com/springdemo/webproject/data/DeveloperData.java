package com.springdemo.webproject.data;

import com.springdemo.webproject.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperData extends JpaRepository<Developer,Integer> {
    public List<Developer> findByTeamId(Integer teamId);
}
