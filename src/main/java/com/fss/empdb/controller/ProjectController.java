package com.fss.empdb.controller;


import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.SearchCriteria;
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
@RequestMapping("/projectservice/v1")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    //Get All Project
    @GetMapping("/get-all-project")
    public ResponseEntity<List<Project>> getAllProject() {
        log.info("-------getAllProject---------" );
        return ResponseEntity.ok().body(projectService.getAllProject());
        //return ResponseEntity.ok().body(projectService.getAllProject());
    }

    //Get Project Details By Id - View Case
    @GetMapping("/get-projectById/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
        log.info("-------getAllProject---------" + projectId);
        return ResponseEntity.ok().body(projectService.getProjectById(projectId));
    }


    //Get Project Details By Id - View Case
    @GetMapping("/get-projectByName/{name}")
    public ResponseEntity<Project> getProjectByName(@PathVariable(value = "name") String projectName) {
        //LOGGER.info("-------projectName---------" + projectName);
        return ResponseEntity.ok().body(projectService.getProjectByName(projectName));
    }

    //Add & Update Project
    @PostMapping(value = "/project-add-update")
    public ResponseEntity<Project> createOrUpdateProject(@RequestBody Project project)  {
       // LOGGER.info("-------Project---------" + project);

        Project proj = projectService.createOrUpdateProject(project);
        return new ResponseEntity<Project>(proj, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/project-delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(value = "id") Long projectId){
        //LOGGER.info("-------Project---------" + projectId);

        projectService.deleteProject(projectId);
        return new ResponseEntity<Project>(new HttpHeaders(), HttpStatus.OK);
    }

}
