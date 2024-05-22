package com.Redcorpmicroservice.workAndProyect.repository;

import com.Redcorpmicroservice.workAndProyect.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
