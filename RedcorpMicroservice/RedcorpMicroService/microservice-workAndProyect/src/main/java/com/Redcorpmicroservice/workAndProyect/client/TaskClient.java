package com.Redcorpmicroservice.workandproyect.client;

import com.Redcorpmicroservice.workandproyect.model.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-task", url = "172.31.11.92:8070")
public interface TaskClient {
    @GetMapping("/api/redcorp/v1/task/search-by-project/{projectId}")
    List<TaskDto> findAllTasksByProjectId(@PathVariable Long projectId);
}
