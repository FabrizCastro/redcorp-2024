package com.Redcorpmicroservice.workAndProyect.service;

import com.Redcorpmicroservice.workAndProyect.model.Project;
import com.Redcorpmicroservice.workAndProyect.model.dto.TaskByProjectResponse;

import java.util.List;

public interface ProjectService {
    public abstract Project createProject(Project project);
    public abstract Project getProjectById(Long project_id);
    public abstract Project updateProject(Project project);
    public abstract void deleteProject(Long project_id);
    public abstract List<Project> getAll();

    List<Project> findByTeamId(Long teamId);

    TaskByProjectResponse findTasksByProjectId(Long projectId);
}
