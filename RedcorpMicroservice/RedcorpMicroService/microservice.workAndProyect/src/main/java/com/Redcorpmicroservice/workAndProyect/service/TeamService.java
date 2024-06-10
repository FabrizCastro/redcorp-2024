package com.Redcorpmicroservice.workAndProyect.service;

import com.Redcorpmicroservice.workAndProyect.model.Team;
import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeByTeamResponse;
import com.Redcorpmicroservice.workAndProyect.model.dto.ProjectByTeamResponse;

import java.util.List;

public interface TeamService {

    List<Team> findAll();
    Team findById(Long id);
    void save (Team team);

    Team updateTeam(Team team);

    List<Team> findAllBySectionId(Long sectionId);
    EmployeeByTeamResponse findEmployeesByTeamId (Long teamId);

    ProjectByTeamResponse findProjectsByTeamId (Long teamId);
}
