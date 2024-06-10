package com.Redcorpmicroservice.workAndProyect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRequest {
    private String teamName;
    private String teamDescription;
    private Long sectionId;
}
