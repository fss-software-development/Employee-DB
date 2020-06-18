package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.DuplicateRecordException;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.*;
import com.fss.empdb.util.ExceptionHandlerValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProjectService {


    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StatusRepository statusRepository;


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

        Project project = new Project();
        project.setProjectId(projectId);
        projectRepository.delete(project);
    }

    public Project createProject(Project project) {
        Project savedProject = null;
        try {
            project.setInsUser(Long.valueOf(1));
            project.setLastUpdateUser(Long.valueOf(1));
            project.setInsDate(new Date());
            project.setLastUpdateDate(new Date());
            savedProject = projectRepository.save(project);

        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.projectDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        } catch (NullPointerException ex) {
            log.error("null record", ex);
            //   throw new MethodArgumentNotValidException();
        }
        return savedProject;
    }

    public Project updateProject(Project project) {
        Project updateProject = null;
        try {
            Optional<Project> pro = projectRepository.findById(project.getProjectId());
            Project proEntity = pro.get();
            project.setInsUser(proEntity.getInsUser());
            project.setLastUpdateUser(Long.valueOf(1));
            project.setInsDate(proEntity.getInsDate());
            project.setLastUpdateDate(new Date());
            updateProject = projectRepository.save(project);
        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.projectDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        } catch (NullPointerException ex) {
            log.error("null record", ex);
            //  throw new MethodArgumentNotValidException();
        }
        return updateProject;
    }

    public List<Project> projectsBySearch(ProjectSearchCriteria proj) {

        Optional<Status> st = statusRepository.findById(Long.valueOf(6));
        Status statusEntity = st.get();

        log.info("Project  " + proj);
        return projectRepository.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!(proj.getProjectName().isEmpty())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("projectName"), "%" + proj.getProjectName() + "%")));
                }
                if (proj.getProducts().length > 0) {
                    Join<Project, Product> phoneJoin = root.join("product");
                    predicates.add(phoneJoin.in(proj.getProducts()));
                }
                if (proj.getRegion().length > 0) {
                    Join<Project, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(proj.getRegion()));
                }
                if (proj.getAccount().length > 0) {
                    Join<Project, Account> phoneJoin = root.join("account");
                    predicates.add(phoneJoin.in(proj.getAccount()));
                }
                if (proj.getServiceLine().length > 0) {
                    Join<Project, ServiceLine> phoneJoin = root.join("serviceLine");
                    predicates.add(phoneJoin.in(proj.getServiceLine()));
                }
                if (proj.getServiceTypes().length > 0) {
                    Join<Project, ServiceType> phoneJoin = root.join("serviceTypes");
                    predicates.add(phoneJoin.in(proj.getServiceTypes()));
                }
                if (proj.getStatus().length > 0) {
                    Join<Project, Status> phoneJoin = root.join("status");
                    predicates.add(phoneJoin.in(proj.getStatus()));
                }

                Join<Project, Status> phoneJoin = root.join("status");
                Predicate status =phoneJoin.in(statusEntity).not();
                predicates.add(status);

                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
