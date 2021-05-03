package com.springdemo.webproject.data;

import com.springdemo.webproject.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamData extends JpaRepository<Team,Integer> {
    public Team findByName(String name);
}
