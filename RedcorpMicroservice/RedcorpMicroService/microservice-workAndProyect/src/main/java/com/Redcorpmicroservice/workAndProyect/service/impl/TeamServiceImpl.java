package com.Redcorpmicroservice.workandproyect.service.impl;

import com.Redcorpmicroservice.workandproyect.client.EmployeeClient;
import com.Redcorpmicroservice.workandproyect.exception.ValidationException;
import com.Redcorpmicroservice.workandproyect.model.Project;
import com.Redcorpmicroservice.workandproyect.model.Team;
import com.Redcorpmicroservice.workandproyect.model.dto.EmployeeByTeamResponse;
import com.Redcorpmicroservice.workandproyect.model.dto.EmployeeDto;
import com.Redcorpmicroservice.workandproyect.model.dto.ProjectByTeamResponse;
import com.Redcorpmicroservice.workandproyect.repository.TeamRepository;
import com.Redcorpmicroservice.workandproyect.service.ProjectService;
import com.Redcorpmicroservice.workandproyect.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ProjectService projectService;

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
        if(team.getTeamName() == null || team.getTeamName().isEmpty()){
            throw new ValidationException("Es obligatorio el nombre del equipo.");
        }
        if(team.getTeamDescription() == null || team.getTeamDescription().isEmpty()){
            throw new ValidationException("Es obligatoria la descripción del equipo.");
        }
        teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        Team teamFind = findById(team.getId());

        if(teamRepository.existsById(team.getSectionId()))
        {
            if(team.getTeamName() == null || team.getTeamName().isEmpty()){
                team.setTeamName(teamFind.getTeamName());
            }
            if(team.getTeamDescription() == null || team.getTeamDescription().isEmpty()){
                team.setTeamDescription(teamFind.getTeamDescription());
            }
            return teamRepository.save(team);
        }
        else {
            throw new ValidationException("No existe el equipo.");
        }

    }

    @Override
    public List<Team> findAllBySectionId(Long sectionId) {
        return teamRepository.findAllBySectionId(sectionId);
    }

    @Override
    public EmployeeByTeamResponse findEmployeesByTeamId(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(new Team());
        List<EmployeeDto> employeeDtoList = employeeClient.findAllEmployeeByTeam(teamId);

        return EmployeeByTeamResponse.builder()
                .teamName(team.getTeamName())
                .teamDescription(team.getTeamDescription())
                .sectionId(team.getSectionId())
                .employeeDtoList(employeeDtoList)
                .build();
    }

    @Override
    public ProjectByTeamResponse findProjectsByTeamId(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(new Team());

        List<Project> projectList = projectService.findByTeamId(teamId);

        return ProjectByTeamResponse.builder()
                .teamName(team.getTeamName())
                .teamDescription(team.getTeamDescription())
                .sectionId(team.getSectionId())
                .projectDtoList(projectList)
                .build();
    }
}
