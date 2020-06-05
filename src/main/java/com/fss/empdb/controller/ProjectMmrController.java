package com.fss.empdb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fss.empdb.DTO.ProjectMMRDto;
import com.fss.empdb.domain.*;
import com.fss.empdb.service.ProjectMmrService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/projects/mmr")
public class ProjectMmrController {

    @Autowired
    ProjectMmrService projectMmrService;

    @PreAuthorize("hasAnyAuthority('VIEW_MMR')")
    @GetMapping("/")
    public ResponseEntity<ProjectMMRDto> allProjectMmr() {
        return ResponseEntity.ok().body(projectMmrService.allProjectMmr());
    }

    @PreAuthorize("hasAnyAuthority('VIEW_MMR')")
    @GetMapping("/search/{projectId}/{year}")
    public ResponseEntity<ProjectMMRDto> projectMMRSearch(@PathVariable(value = "projectId") Long projectId,
                                                          @PathVariable(value = "year") Long year) {
        return ResponseEntity.ok().body(projectMmrService.projectMmrByView(projectId, year));
    }

    @PreAuthorize("hasAnyAuthority('ADD_MMR')")
    @PostMapping
    public ResponseEntity<String> createProjectMmr(@RequestBody ProjectMMRDto dto) throws JsonProcessingException {
        log.info("Project MMR ADD " + dto);

        for (ProjectMMR mmr : dto.getMmr()
        ) {
            mmr.setProject(dto.getProject());
//            mmr.setYear(dto.getFinancialYear().longValue());
//            mmr.setYear();
            projectMmrService.createProjectMmr(mmr);
        }
        return ResponseEntity.ok().body("success");
    }

    @PreAuthorize("hasAnyAuthority('SEARCH_MMR')")
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<ProjectMMRDto>> projectsBySearch(@RequestBody ProjectMMRSearchCriteria projectMMRSearchCriteria)  {
        return ResponseEntity.ok().body(projectMmrService.projectsMmrBySearch(projectMMRSearchCriteria));
    }

}
