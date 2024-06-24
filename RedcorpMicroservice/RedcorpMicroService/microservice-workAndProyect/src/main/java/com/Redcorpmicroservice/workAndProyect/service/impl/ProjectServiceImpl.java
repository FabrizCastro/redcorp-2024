package com.Redcorpmicroservice.workandproyect.service.impl;

import com.Redcorpmicroservice.workandproyect.client.TaskClient;
import com.Redcorpmicroservice.workandproyect.exception.ValidationException;
import com.Redcorpmicroservice.workandproyect.model.Project;
import com.Redcorpmicroservice.workandproyect.model.dto.TaskByProjectResponse;
import com.Redcorpmicroservice.workandproyect.model.dto.TaskDto;
import com.Redcorpmicroservice.workandproyect.repository.ProjectRepository;
import com.Redcorpmicroservice.workandproyect.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskClient taskClient;
    @Override
    public Project createProject(Project project) {
        validateProject(project);
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long project_id) {
        if(projectRepository.existsById(project_id))
            return projectRepository.findById(project_id).orElse(null);
        else
            throw new ValidationException("No existe el proyecto");
    }

    @Override
    public Project updateProject(Project project) {

        if(projectRepository.existsById(project.getId())) {
            validateProject(project);
            return projectRepository.save(project);
        }
        else
            throw new ValidationException("No existe el proyecto");

    }

    @Override
    public void deleteProject(Long project_id) {
        projectRepository.deleteById(project_id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findByTeamId(Long teamId) {
        return projectRepository.findAllByTeamId(teamId);
    }

    @Override
    public TaskByProjectResponse findTasksByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(new Project());

        List<TaskDto> taskDtoList = taskClient.findAllTasksByProjectId(projectId);

        return TaskByProjectResponse.builder()
                .projectName(project.getProjectName())
                .projectDescription(project.getProjectDescription())
                .projectInitialDate(project.getProjectInitialDate())
                .projectState(project.getProjectState())
                .teamId(project.getTeamId())
                .taskDtoList(taskDtoList)
                .build();
    }

    private void validateProject(Project project){

        List<String> validStates = Arrays.asList("In progress", "Completed", "To Do","In revision");

        if(project.getProjectName() == null || project.getProjectName().isEmpty()){
            throw new ValidationException("El nombre del proyecto debe ser obligatorio");
        }
        if(project.getProjectName().length() > 50 && project.getProjectName().length()<3) {
            throw new ValidationException("El nombre del proyecto es muy corto o muy largo");
        }
        if(project.getProjectDescription() == null || project.getProjectDescription().isEmpty()){
            throw new ValidationException("La descripción del proyecto debe ser obligatoria");
        }
        if(project.getProjectDescription().length() > 100){
            throw  new ValidationException("La descripción del projecto es demasiado largo");
        }
        if(project.getProjectInitialDate() == null){
            throw new ValidationException("La fecha de inicio es obligatoria");
        }
        if(project.getProjectFinalDate() == null){
            throw new ValidationException("La fecha de fin es obligatoria");
        }
        if(project.getProjectState() == null || project.getProjectState().isEmpty()) {
            throw new ValidationException("El estado del proyecto es obligatorio");
        }
        if(!validStates.contains(project.getProjectState())){
            throw new ValidationException("El estado del proyecto no es válido");
        }
        if(project.getTeamId() == null || project.getTeamId().describeConstable().isEmpty()) {
            throw new ValidationException("El equipo asignado tiene que ser válido");
        }
    }

}
