package com.Redcorpmicroservice.workAndProyect.repository;

import com.Redcorpmicroservice.workAndProyect.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    boolean existsById(Long projectId);
    List<Project> findAll();
    List<Project> findAllByTeamId(Long teamId);
}
