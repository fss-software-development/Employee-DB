package com.fss.empdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fss.empdb.domain.ProjectMMR;
import com.fss.empdb.domain.ProjectMMRDto;
import com.fss.empdb.service.ProjectMmrService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/projects/mmr")
public class ProjectMmrController {

    @Autowired
    ProjectMmrService projectMmrService;

    @GetMapping("/")
    public ResponseEntity<ProjectMMRDto> allProjectMmr() {
        return ResponseEntity.ok().body(projectMmrService.allProjectMmr());
    }

    @PostMapping("/search/{projectId}/{year}")
    public ResponseEntity<ProjectMMRDto> projectMMRSearch(@PathVariable(value = "projectId") Long projectId,
                                                          @PathVariable(value = "year") Long year) {
           return ResponseEntity.ok().body(projectMmrService.projectMmrBySearch(projectId, year));
    }

    @PostMapping
    public ResponseEntity<String> createProjectMmr(@RequestBody ProjectMMRDto dto) throws JsonProcessingException {
        log.info("Project MMR ADD " + dto);

        for (ProjectMMR mmr : dto.getMmr().values()
        ) {
            mmr.setProject(dto.getProject());
            mmr.setYear(dto.getFinancialYear().longValue());
            projectMmrService.createProjectMmr(mmr);
        }
        return ResponseEntity.ok().body("success");
    }

    @PutMapping
    public ResponseEntity<String> updateProjectMmr(@RequestBody ProjectMMRDto dto) throws JsonProcessingException {
        log.info("Project MMR ADD " + dto);

        for (ProjectMMR mmr : dto.getMmr().values()
        ) {
            mmr.setProject(dto.getProject());
            mmr.setYear(dto.getFinancialYear().longValue());
            projectMmrService.updateProjectMmr(mmr);
        }
        return ResponseEntity.ok().body("updated");
    }

}
