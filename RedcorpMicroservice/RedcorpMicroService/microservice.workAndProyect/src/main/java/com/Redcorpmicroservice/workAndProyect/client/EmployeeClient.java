package com.Redcorpmicroservice.workAndProyect.client;

import com.Redcorpmicroservice.workAndProyect.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-AuthAndProfile", url = "localhost:8090")
public interface EmployeeClient {

    @GetMapping("/api/redcorp/v1/employee/search-by-section/{sectionId}")
    List<EmployeeDto> findAllEmployeeBySection(@PathVariable Long sectionId);
}
