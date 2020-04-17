package com.fss.empdb.controller;


import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.SearchCriteria;
import com.fss.empdb.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectservice/v1")
public class ProjectController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectService;

    //Get All Project
    @GetMapping("/get-all-project")
    public ResponseEntity<List<Project>> getAllProject() {
        return ResponseEntity.ok().body(projectService.getAllProject());
        //return ResponseEntity.ok().body(projectService.getAllProject());
    }

    //Get Project Details By Id - View Case
    @GetMapping("/get-all-project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(projectService.getProjectById(employeeId));
    }

}
