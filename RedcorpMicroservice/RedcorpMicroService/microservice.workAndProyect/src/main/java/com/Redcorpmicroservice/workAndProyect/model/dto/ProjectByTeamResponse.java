package com.Redcorpmicroservice.workAndProyect.model.dto;

import com.Redcorpmicroservice.workAndProyect.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectByTeamResponse {
    private String teamName;
    private String teamDescription;
    private Long sectionId;
    private List<Project> projectDtoList;
}
