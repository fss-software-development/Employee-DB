package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.ProjectMmrRepository;
import com.fss.empdb.repository.ProjectRepository;
import com.fss.empdb.repository.RegionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProjectMmrService {
    @Autowired
    ProjectMmrRepository projectMmrRepository;

    @Autowired
    ProjectRepository projectRepository;

    public List<ProjectMMR> allProjectMmr() {
        return projectMmrRepository.findAll();
    }

    public List<ProjectMMR> projectMmrBySearch(Long projectId, Long Year) {
        List<ProjectMMR> projectMMRS = null;
        Optional<Project> proj = projectRepository.findById(projectId);
        Project projEntity = proj.get();
        try {
            projectMMRS = projectMmrRepository.findAll(new Specification<ProjectMMR>() {
                @Override
                public Predicate toPredicate(Root<ProjectMMR> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();
                    if (projEntity != null) {
                        Join<ProjectMMR, Project> phoneJoin = root.join("project");
                        predicates.add(phoneJoin.in(projEntity));
                    }
                    if (Year != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("year"), +Year)));
                    }
                    log.info("Search filter Size :" + predicates.size());
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("projectMmrId")));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            });
        } catch (Exception e) {
            log.error("ERROR_LOG" + e);
        }
        return projectMMRS;
    }

    public ProjectMMR createProjectMmr(ProjectMMR projectMMR) {
        projectMMR.setInsUser(Long.valueOf(1));
        projectMMR.setLastUpdateUser(Long.valueOf(1));
        projectMMR.setInsDate(new Date());
        projectMMR.setLastUpdateDate(new Date());
        return projectMmrRepository.save(projectMMR);
    }

    public ProjectMMR updateProjectMmr(ProjectMMR projectMMR) {
        Optional<ProjectMMR> pro = projectMmrRepository.findById(projectMMR.getProjectMmrId());
        ProjectMMR proMmrEntity = pro.get();
        projectMMR.setInsUser(proMmrEntity.getInsUser());
        projectMMR.setLastUpdateUser(proMmrEntity.getLastUpdateUser());
        projectMMR.setInsDate(new Date());
        projectMMR.setLastUpdateDate(new Date());
        return projectMmrRepository.save(projectMMR);
    }
}
