package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.ProjectController;
import com.fss.empdb.domain.Project;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.*;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProjectService {

    private static Logger log = Logger.getLogger(ProjectController.class);


    @Autowired
    ProjectRepository projectRepository;



    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.DATA_NOT_FOUND + projectId));
    }
}
