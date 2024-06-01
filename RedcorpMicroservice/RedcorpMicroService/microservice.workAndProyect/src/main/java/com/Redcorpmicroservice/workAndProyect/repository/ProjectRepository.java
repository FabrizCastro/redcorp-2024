package com.Redcorpmicroservice.workAndProyect.repository;

import com.Redcorpmicroservice.workAndProyect.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
