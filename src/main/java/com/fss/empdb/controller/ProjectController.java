package com.fss.empdb.controller;


import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.ProjectSearchCriteria;
import com.fss.empdb.exception.ErrorDetails;
import com.fss.empdb.repository.ProjectRepository;
import com.fss.empdb.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    @PreAuthorize("hasAnyAuthority('VIEW_PROJECT')")
    @GetMapping("/")
    public ResponseEntity<List<Project>> allProject() {
            return ResponseEntity.ok().body(projectService.allProject());
    }

    @PreAuthorize("hasAnyAuthority('VIEW_PROJECT')")
    @GetMapping("/{id}")
    public ResponseEntity<Project> projectsById(@PathVariable(value = "id") Long projectId) {
        try {
            return ResponseEntity.ok().body(projectService.projectsById(projectId));
        }catch(Exception ex){
            ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
             return new ResponseEntity(errorDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('SEARCH_PROJECT')")
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Project>> projectsBySearch(@RequestBody ProjectSearchCriteria projSearch)  {
        return ResponseEntity.ok().body(projectService.projectsBySearch(projSearch));
    }

    @PreAuthorize("hasAnyAuthority('ADD_PROJECT')")
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project)  {
        return ResponseEntity.ok().body(projectService.createProject(project));
    }

    @PreAuthorize("hasAnyAuthority('EDIT_PROJECT')")
    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        return ResponseEntity.ok().body(projectService.updateProject(project));
    }

    @PreAuthorize("hasAnyAuthority('DELETE_PROJECT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable(value = "id") Long projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<Project>(new HttpHeaders(), HttpStatus.OK);
    }


}
