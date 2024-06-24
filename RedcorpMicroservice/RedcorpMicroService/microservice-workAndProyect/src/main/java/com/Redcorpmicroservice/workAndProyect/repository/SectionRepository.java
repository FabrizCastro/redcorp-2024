package com.Redcorpmicroservice.workandproyect.repository;

import com.Redcorpmicroservice.workandproyect.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    boolean existsById(Long sectionId);

}
