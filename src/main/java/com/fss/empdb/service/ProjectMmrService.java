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
import java.util.*;

@Log4j2
@Service
public class ProjectMmrService {
    @Autowired
    ProjectMmrRepository projectMmrRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    public ProjectMMRDto allProjectMmr() {
        List<ProjectMMR> list = projectMmrRepository.findAll();
        Map<String, ProjectMMRDto> map = mmrListAsMap(list);
        return (ProjectMMRDto) map.values().toArray()[0];
    }

    public ProjectMMRDto projectMmrBySearch(Long projectId, Long Year) {
        log.info("Service" + projectId + "," + Year);
        List<ProjectMMR> projectMMRS = null;
        ProjectMMRDto projectMMRDto = null;

//        Project projEntity = projectService.projectsById(projectId);

        Optional<Project> proj = projectRepository.findById(projectId);
        Project projEntity = proj.get();

        log.info("Project :" + projEntity);
        try {
            log.info("Inside try :" + projectMMRS);
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

            if ((projectMMRS.isEmpty())) {
                log.info("Empty Record");
                new ResourceNotFoundException(ErrorConstants.SEARCH_DATA_NOT_FOUND);
            } else {
                log.info("Record Available");
                Map<String, ProjectMMRDto> map = mmrListAsMap(projectMMRS);
                projectMMRDto = (ProjectMMRDto) map.values().toArray()[0];
                log.info("Record Available 2" + projectMMRDto);

            }
        } catch (Exception e) {
            log.error("ERROR_LOG" + e);
        }

        return projectMMRDto;
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

    private Map<String, ProjectMMRDto> mmrListAsMap(List<ProjectMMR> list) {

        log.info("in list map :" + list.toString());
        Map<String, ProjectMMRDto> map = new HashMap<>();

        for (ProjectMMR mmr : list) {
            String key = mmr.getProject().getProjectId() + ":" + mmr.getYear();
            ProjectMMRDto dto = map.get(key);
            if (dto == null) {
                dto = new ProjectMMRDto();
                dto.setProject(mmr.getProject());
                dto.setFinancialYear(mmr.getYear().longValue());
                dto.setMmr(new HashMap<>());
                map.put(key, dto);
            }
            dto.getMmr().put(mmr.getMonth(), mmr);
//            mmr.setVariance1(mmr.getBudgetedValue().subtract(mmr.getActualValue()));
//            mmr.setVariance2(mmr.getForecastedValue().subtract(mmr.getActualValue()));
        }
        log.info("Map : " + map);
        return map;
    }
}
