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
    public ResponseEntity<List<ProjectMMR>> allProjectMmr() {
        return ResponseEntity.ok().body(projectMmrService.allProjectMmr());
    }

    @PostMapping("/search/{projectId}/{year}")
    public ResponseEntity<List<ProjectMMR>> projectMMRSearch(@PathVariable(value = "projectId") Long projectId,
                                                             @PathVariable(value = "year") Long year) {
        return ResponseEntity.ok().body(projectMmrService.projectMmrBySearch(projectId, year));
    }

    @PostMapping
    public ResponseEntity<String> createProjectMmr(@RequestBody ProjectMMR[] projectMMR) throws JsonProcessingException {
        log.info("Project MMR ADD " + projectMMR);

        String result = "";
        int count = 0;

        for (int i = 0; i < projectMMR.length; i++) {
            count++;
            projectMmrService.createProjectMmr(projectMMR[i]);
        }
        result = "Success";
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public ResponseEntity<String> updateProjectMmr(@RequestBody ProjectMMR[] projectMMR) throws JsonProcessingException {
        log.info("Project MMR ADD " + projectMMR);

        String result = "";
        int count = 0;

        for (int i = 0; i < projectMMR.length; i++) {
            count++;
            projectMmrService.updateProjectMmr(projectMMR[i]);
        }
        result = "Updated " + count + " record successfully";
        return ResponseEntity.ok().body(result);
    }

}
