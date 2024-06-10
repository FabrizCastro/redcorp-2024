package com.Redcorpmicroservice.workAndProyect.repository;

import com.Redcorpmicroservice.workAndProyect.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<  Team,Long> {

    boolean existsById(Long teamId);
    List<Team> findAllBySectionId(Long teamId);

    List<Team> findAll();
}
