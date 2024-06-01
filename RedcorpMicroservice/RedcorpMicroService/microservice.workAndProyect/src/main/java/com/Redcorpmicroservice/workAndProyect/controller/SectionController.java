package com.Redcorpmicroservice.workAndProyect.controller;

import com.Redcorpmicroservice.workAndProyect.model.Section;
import com.Redcorpmicroservice.workAndProyect.model.dto.SectionRequest;
import com.Redcorpmicroservice.workAndProyect.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/redcorp/v1/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    // URL: http://localhost:9090/api/redcorp/v1/section
    // Method: POST

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSection(@RequestBody SectionRequest sectionRequest) {
        Section section = new Section();
        section.setSectionName(sectionRequest.getSectionName());
        section.setSectionDescription(sectionRequest.getSectionDescription());

        sectionService.save(section);
    }

    @GetMapping
    public ResponseEntity<?>findAllSections(){
        return ResponseEntity.ok(sectionService.findAll());
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<?> findById(@PathVariable Long sectionId)
    {
        return ResponseEntity.ok(sectionService.findById(sectionId));
    }
    @GetMapping("/search-employee/{sectionId}")
    public ResponseEntity<?> findEmployeesBySectionId(@PathVariable Long sectionId){
        return ResponseEntity.ok(sectionService.findEmployeesBySectionId(sectionId));
    }

    @GetMapping("/search-team/{sectionId}")
    public ResponseEntity<?> findTeamsBySectionId(@PathVariable Long sectionId){
        return ResponseEntity.ok(sectionService.findTeamsBySectionId(sectionId));
    }


}
