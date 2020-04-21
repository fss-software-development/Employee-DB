package com.fss.empdb.controller;


import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.ProjectCriteria;
import com.fss.empdb.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProject() {
            return ResponseEntity.ok().body(projectService.getAllProject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
        log.info("-------getAllProject---------" + projectId);
        return ResponseEntity.ok().body(projectService.getProjectById(projectId));
    }

    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Project>> getProjectBySearchCriteria(@RequestBody ProjectCriteria projSearch)  {
        log.info("-------getProjectBySearchCriteria---------" + projSearch);
        return ResponseEntity.ok().body(projectService.findByProject(projSearch));
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project)  {
        Project proj = projectService.createProject(project);
        return new ResponseEntity<Project>(proj, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        return ResponseEntity.ok().body(projectService.updateProject(project));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(value = "id") Long projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<Project>(new HttpHeaders(), HttpStatus.OK);
    }

}
