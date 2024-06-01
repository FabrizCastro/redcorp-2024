package com.Redcorpmicroservice.workAndProyect.service.impl;

import com.Redcorpmicroservice.workAndProyect.model.Team;
import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeByTeamResponse;
import com.Redcorpmicroservice.workAndProyect.model.dto.ProjectByTeamResponse;
import com.Redcorpmicroservice.workAndProyect.repository.TeamRepository;
import com.Redcorpmicroservice.workAndProyect.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Team> findAllBySectionId(Long sectionId) {
        return teamRepository.findAllBySectionId(sectionId);
    }

    @Override
    public EmployeeByTeamResponse findEmployeesByTeamId(Long teamId) {
        return null;
    }

    @Override
    public ProjectByTeamResponse findProjectsByTeamId(Long teamId) {
        return null;
    }
}
