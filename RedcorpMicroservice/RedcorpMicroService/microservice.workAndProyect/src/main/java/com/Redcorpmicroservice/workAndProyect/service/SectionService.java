package com.Redcorpmicroservice.workAndProyect.service;


import com.Redcorpmicroservice.workAndProyect.model.Section;
import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeBySectionResponse;
import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeByTeamResponse;
import com.Redcorpmicroservice.workAndProyect.model.dto.TeamBySectionResponse;

import java.util.List;

public interface SectionService {

    List<Section> findAll();

    Section findById(Long id);

    void save (Section section);

    EmployeeBySectionResponse findEmployeesBySectionId(Long sectionId);

    TeamBySectionResponse findTeamsBySectionId(Long sectionId);
}
