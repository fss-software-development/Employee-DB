package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.ProjectController;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.*;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@Service
public class ProjectService {



    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ProjectTaggingRepository projectTaggingRepository;


    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.DATA_NOT_FOUND + projectId));
    }

    public Project getProjectByName(String projectName) {
        return projectRepository.findByProjectName(projectName);
    }

    public void deleteProject(Long projectId) {
        Project project=new Project();
        project.setProjectId(projectId);
        projectRepository.delete(project);
    }

    public Project createOrUpdateProject(Project project) {
        try {
            if (project.getProjectId() != null) {
                log.info("----------------------- Update -----------------------");
                Optional<Project> proj = projectRepository.findById(project.getProjectId());
                Project projEntity = proj.get();

                Optional<Account> account = accountRepository.findById(project.getAccount().getAccountId());
                Account accountEntity =account.get();

                Optional<ProjectTagging> projectTagging = projectTaggingRepository.findById(project.getProjectTagging().getProjectTaggingId());
                ProjectTagging projectTaggingEntity =projectTagging.get();

                Optional<Region> region = regionRepository.findById(project.getRegion().getRegionId());
                Region regionEntity =region.get();

                Optional<Department> department = departmentRepository.findById(project.getDepartment().getDepartmentId());
                Department departmentEntity =department.get();

                projEntity.setProjectId(project.getProjectId());
                projEntity.setProjectName(project.getProjectName());
                projEntity.setProjectManager(project.getProjectManager());
                projEntity.setProjectStatus(project.getProjectStatus());
                projEntity.setDepartment(departmentEntity);
                projEntity.setAccount(accountEntity);
                projEntity.setRegion(regionEntity);
                projEntity.setProjectTagging(projectTaggingEntity);

                projEntity.setInsUser(Long.valueOf(1));  //  Change is required
                projEntity.setInsDate( new Date());
                projEntity.setLastUpdateUser(Long.valueOf(1));  //  Change is required
                projEntity.setLastUpdateDate(new Date());
                projEntity = projectRepository.save(projEntity);

                return projEntity;
            } else {
                log.info("----------------------- Save -----------------------");
                project.setInsUser(Long.valueOf(1));
                project.setLastUpdateUser(Long.valueOf(1));
                project.setInsDate(new Date());
                project.setLastUpdateDate(new Date());
                project = projectRepository.save(project);
                return project;
            }
        } catch (Exception e) {
            log.info(e);
        }
        return null;
    }
}
