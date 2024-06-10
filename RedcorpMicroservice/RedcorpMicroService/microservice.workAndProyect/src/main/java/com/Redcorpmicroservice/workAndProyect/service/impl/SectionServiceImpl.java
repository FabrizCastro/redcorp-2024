package com.Redcorpmicroservice.workAndProyect.service.impl;

import com.Redcorpmicroservice.workAndProyect.client.EmployeeClient;
import com.Redcorpmicroservice.workAndProyect.exception.ValidationException;
import com.Redcorpmicroservice.workAndProyect.model.Section;
import com.Redcorpmicroservice.workAndProyect.model.Team;
import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeBySectionResponse;
import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeDto;
import com.Redcorpmicroservice.workAndProyect.model.dto.TeamBySectionResponse;
import com.Redcorpmicroservice.workAndProyect.repository.SectionRepository;
import com.Redcorpmicroservice.workAndProyect.service.SectionService;
import com.Redcorpmicroservice.workAndProyect.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private TeamService teamService;

    @Override
    public List<Section> findAll() {
        return (List<Section>) sectionRepository.findAll();
    }

    @Override
    public Section findById(Long id) {
        return sectionRepository.findById(id).orElseThrow();
    }

    @Override
    public Section save(Section section) {
        if(section.getSectionName() == null ||
                section.getSectionName().isEmpty())
        {
            throw new ValidationException("El nombre de la sección es obligatorio");
        }

        if(section.getSectionDescription() == null ||
                section.getSectionDescription().isEmpty())
        {
            throw new ValidationException("La descripción es obligatorio");

        }

        return sectionRepository.save(section);
    }

    @Override
    public Section updateSection(Section section) {
        Section sectionFind = findById(section.getId());

        if(sectionRepository.existsById(section.getId()))
        {
            if(section.getSectionName() == null ||
            section.getSectionName().isEmpty())
            {
                section.setSectionName(sectionFind.getSectionName());
            }

            if(section.getSectionDescription() == null ||
                    section.getSectionDescription().isEmpty())
            {
                section.setSectionDescription(sectionFind.getSectionDescription());
            }

            return sectionRepository.save(section);
        }
        else
        {
            throw new ValidationException("Los valores de la sección están vacíos o no son correctos");

        }

    }

    @Override
    public EmployeeBySectionResponse findEmployeesBySectionId(Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElse(new Section());

        // Obtener los employees
        List<EmployeeDto> employeeDtoList = employeeClient.findAllEmployeeBySection(section.getId());

        return EmployeeBySectionResponse.builder()
                .sectionName(section.getSectionName())
                .sectionDescription(section.getSectionDescription())
                .employeeDtoList(employeeDtoList)
                .build();
    }

    @Override
    public TeamBySectionResponse findTeamsBySectionId(Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElse(new Section());

        List<Team> teamList = teamService.findAllBySectionId(section.getId());

        return TeamBySectionResponse.builder()
                .sectionName(section.getSectionName())
                .sectionDescription(section.getSectionDescription())
                .teamDtoList(teamList)
                .build();
    }
}
