package com.Redcorpmicroservice.workandproyect.service;


import com.Redcorpmicroservice.workandproyect.model.Section;
import com.Redcorpmicroservice.workandproyect.model.dto.EmployeeBySectionResponse;
import com.Redcorpmicroservice.workandproyect.model.dto.TeamBySectionResponse;

import java.util.List;

public interface SectionService {

    List<Section> findAll();

    Section findById(Long id);

    Section save (Section section);

    Section updateSection(Section section);

    EmployeeBySectionResponse findEmployeesBySectionId(Long sectionId);

    TeamBySectionResponse findTeamsBySectionId(Long sectionId);
}
