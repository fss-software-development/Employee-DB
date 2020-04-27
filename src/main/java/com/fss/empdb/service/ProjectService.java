package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ProjectService {



    @Autowired
    ProjectRepository projectRepository;


    public List<Project> allProject() {
        return projectRepository.findAll();
    }

    public Project projectsById(Long projectId) {
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

   public Project createProject(Project project) {
       project.setInsUser(Long.valueOf(1));
       project.setLastUpdateUser(Long.valueOf(1));
       project.setInsDate(new Date());
       project.setLastUpdateDate(new Date());
        return projectRepository.save(project);
    }

    public Project updateProject(Project project){

        project.setInsUser(Long.valueOf(1));
        project.setLastUpdateUser(Long.valueOf(1));
        project.setInsDate(new Date());
        project.setLastUpdateDate(new Date());
        /*projectRepository.findById(project.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND
                        + project.getProjectId()));*/
        return projectRepository.save(project);
    }

    public List<Project> projectsBySearch(ProjectSearchCriteria proj) {
        return projectRepository.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                /*if (proj.getProjectId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("projectId"), proj.getProjectId())));
                }*/
                if (proj.getProjectName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("projectName"), "%" + proj.getProjectName() + "%")));
                }
               /* if (proj.getProjectManager() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("projectManager"), "%" + proj.getProjectManager() + "%")));
                }
                if (proj.getProjectStatus() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("projectStatus"), "%" + proj.getProjectStatus() + "%")));
                }*/

                /*if (proj.getProjectStatus() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("projectStatus"), "%" + proj.getProjectStatus() + "%")));
                }

                if (proj.getProjectStatus() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("projectStatus"), "%" + proj.getProjectStatus() + "%")));
                }*/

                /*if (proj.getDepartment().length > 0) {
                    Join<Employee, Department> phoneJoin = root.join("department");
                    predicates.add(phoneJoin.in(proj.getDepartment()));
                }*/
                if (proj.getRegion().length > 0) {
                    Join<Employee, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(proj.getRegion()));
                }
                if (proj.getAccount().length > 0) {
                    Join<Employee, Account> phoneJoin = root.join("account");
                    predicates.add(phoneJoin.in(proj.getAccount()));
                }
                /*if (proj.getProjectTagging().length > 0l) {
                    Join<Employee, Account> phoneJoin = root.join("projectTagging");
                    predicates.add(phoneJoin.in(proj.getProjectTagging()));
                }*/
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
