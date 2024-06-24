package com.Redcorpmicroservice.workandproyect.controller;

import com.Redcorpmicroservice.workandproyect.model.Project;
import com.Redcorpmicroservice.workandproyect.model.dto.ProjectRequest;
import com.Redcorpmicroservice.workandproyect.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/redcorp/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectRequest projectRequest) {
        Project project = new Project();
        project.setProjectName(projectRequest.getProjectName());
        project.setProjectDescription(projectRequest.getProjectDescription());
        project.setProjectInitialDate(projectRequest.getProjectInitialDate());
        project.setProjectFinalDate(projectRequest.getProjectFinalDate());
        project.setProjectState(projectRequest.getProjectState());
        project.setTeamId(projectRequest.getTeamId());

        projectService.createProject(project);
    }

    @GetMapping
    public ResponseEntity<?> findAllProjects(){
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> findById(@PathVariable(name = "projectId") Long projectId)
    {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable(name = "projectId") Long projectId, @RequestBody ProjectRequest projectRequest) {

        Project project = projectService.getProjectById(projectId);

        if(project == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        project.setProjectName(projectRequest.getProjectName());
        project.setProjectDescription(projectRequest.getProjectDescription());
        project.setProjectInitialDate(projectRequest.getProjectInitialDate());
        project.setProjectFinalDate(projectRequest.getProjectFinalDate());
        project.setProjectState(projectRequest.getProjectState());
        project.setTeamId(projectRequest.getTeamId());

        return new ResponseEntity<Project>(projectService.updateProject(project), HttpStatus.OK);
    }

    @GetMapping("/search-task/{projectId}")
    public ResponseEntity<?> findTasksByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(projectService.findTasksByProjectId(projectId));
    }





}
