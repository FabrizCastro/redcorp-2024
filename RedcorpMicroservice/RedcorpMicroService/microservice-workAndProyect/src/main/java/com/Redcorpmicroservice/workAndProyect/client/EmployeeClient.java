package com.Redcorpmicroservice.workandproyect.client;

import com.Redcorpmicroservice.workandproyect.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-AuthAndProfile", url = "172.31.11.92:8090")
public interface EmployeeClient {

    @GetMapping("/api/redcorp/v1/employee/search-by-section/{sectionId}")
    List<EmployeeDto> findAllEmployeeBySection(@PathVariable Long sectionId);

    @GetMapping("/api/redcorp/v1/employee/search-by-team/{teamId}")
    List<EmployeeDto> findAllEmployeeByTeam(@PathVariable Long teamId);
}
