package com.Redcorpmicroservice.workAndProyect.model.dto;

import com.Redcorpmicroservice.workAndProyect.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamBySectionResponse {
    private String sectionName;
    private String sectionDescription;
    private List<Team> teamDtoList;
}
