package com.fss.empdb.service;

import com.fss.empdb.DTO.ProjectMMRDto;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.ProjectMmrRepository;
import com.fss.empdb.repository.ProjectRepository;
import com.fss.empdb.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Log4j2
@Service
public class ProjectMmrService {
    @Autowired
    ProjectMmrRepository projectMmrRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @Autowired
    Util util;

    public ProjectMMRDto allProjectMmr() {
        List<ProjectMMR> list = projectMmrRepository.findAll();
        ProjectMMRDto map = mmrList(list);
        return map;
    }

    public ProjectMMRDto projectMmrByView(Long projectId, Long Year) {
        log.info("Service" + projectId + "," + Year);
        List<ProjectMMR> projectMMRS = null;
        ProjectMMRDto projectMMRDto = null;

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
                throw new ResourceNotFoundException(ErrorConstants.SEARCH_MMR_NOT_FOUND);
            } else {
                log.info("Record Available");
                projectMMRDto = mmrList(projectMMRS);
            }
        } catch (ResourceNotFoundException e) {
            log.error("ERROR_LOG" + e);
            throw new ResourceNotFoundException(ErrorConstants.SEARCH_MMR_NOT_FOUND);
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

    public List<ProjectMMRDto> projectsMmrBySearch(ProjectMMRSearchCriteria searchCriteria) {

        log.info("SEARCH CRITERIA" + searchCriteria.getReportType());
        log.info("SEARCH CRITERIA" + searchCriteria.getFinancialYear());

        List<ProjectMMR> projectMMRS = null;
        List<ProjectMMRDto> projectMMRDto = null;
        List<Project> projectList = projectList(searchCriteria.getRegion(), searchCriteria.getAccount(), searchCriteria.getServiceLine()
                , searchCriteria.getServiceTypes(), searchCriteria.getProducts());
        log.info("Project List" + projectList.toString());

        if (searchCriteria.getProject().length > 0) {
            log.info("Inside if" + searchCriteria.getProject().toString());
            for (Project pro : searchCriteria.getProject()) {
                Optional<Project> project = projectRepository.findById(pro.getProjectId());
                Project projectEnitity = project.get();
                projectList.add(projectEnitity);
            }
        }

        Project[] pro = (Project[]) projectList.toArray(new Project[0]);

        try {

            projectMMRS = projectMmrRepository.findAll(new Specification<ProjectMMR>() {
                @Override
                public Predicate toPredicate(Root<ProjectMMR> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();
                    if (pro != null) {
                        Join<ProjectMMR, Project> phoneJoin = root.join("project");
                        predicates.add(phoneJoin.in(pro));
                    }
                    if (searchCriteria.getFinancialYear() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("year"), +searchCriteria.getFinancialYear())));
                    }
                    log.info("Search filter Size :" + predicates.size());
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get("projectMmrId")));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            });

            if ((projectMMRS.isEmpty())) {
                log.info("Empty Record");
                throw new ResourceNotFoundException(ErrorConstants.SEARCH_MMR_NOT_FOUND);
            } else {
                log.info("Record Available Search" + searchCriteria.getReportType());

                if (!(searchCriteria.getReportType().isEmpty())) {

                    log.info("searchCriteria.getMonQutYear()" + searchCriteria.getReportType());

                    switch (searchCriteria.getReportType().toUpperCase()) {
                        case "MONTHLY":
                            projectMMRDto = mmrListDto(projectMMRS);
                            break;
                        case "QUATERLY":
                            projectMMRDto = mmrQuarterly(projectMMRS);
                            break;
                        case "YEARLY":
                            projectMMRDto = mmrYearly(projectMMRS);
                            break;
                    }
                }
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(ErrorConstants.SEARCH_MMR_NOT_FOUND);
        }
        return projectMMRDto;
    }

    private ProjectMMRDto mmrList(List<ProjectMMR> list) {
        ProjectMMRDto map = new ProjectMMRDto();
        List<ProjectMMR> projectMMRSList = new ArrayList<>();

        int count = 0;
        for (ProjectMMR mmr : list) {
            BigDecimal v1 = BigDecimal.ZERO;
            BigDecimal v2 = BigDecimal.ZERO;
            log.info("V1 :" + v1);
            log.info("V2 :" + v2);
            log.info("Count ------------------------------" + count++);
            map.setProject(mmr.getProject());
            map.setFinancialYear(mmr.getYear().longValue());
            v1 = v1.add((mmr.getBudgetedValue().subtract(mmr.getActualValue())));
            v2 = v2.add((mmr.getForecastedValue().subtract(mmr.getActualValue())));
            log.info("V1 :" + v1);
            log.info("V2 :" + v2);
            mmr.setVariance1(v1);
            mmr.setVariance2(v2);
            projectMMRSList.add(mmr);
        }
        map.setMmr(projectMMRSList);
        return map;
    }

    private List<ProjectMMRDto> mmrListDto(List<ProjectMMR> list) {

        List<ProjectMMRDto> map = new ArrayList<ProjectMMRDto>();
        Set<ProjectMMRDto> hashsetList = new HashSet<ProjectMMRDto>();
        for (ProjectMMR mmr : list) {
            ProjectMMRDto dto = new ProjectMMRDto();
            dto = projectMmrByView(mmr.getProject().getProjectId().longValue(), mmr.getYear().longValue());
            hashsetList.add(dto);
            map.add(dto);
        }

        List<ProjectMMRDto> mapNew = map
                .stream()
                .filter(util.distinctByKeys(ProjectMMRDto::getProject, ProjectMMRDto::getFinancialYear))
                .collect(Collectors.toList());

        List<ProjectMMRDto> mmrDtoList = convertSetToList(hashsetList);

        return mapNew;
    }

    private List<Project> projectList(Region[] regions, Account[] accounts, ServiceLine[] serviceLine,
                                      ServiceType[] serviceTypes, Product[] products) {
        List<Project> proj = projectRepository.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (regions.length > 0) {
                    Join<Project, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(regions));
                }
                if (accounts.length > 0) {
                    Join<Project, Account> phoneJoin = root.join("account");
                    predicates.add(phoneJoin.in(accounts));
                }
                if (serviceLine.length > 0) {
                    Join<Project, ServiceLine> phoneJoin = root.join("serviceLine");
                    predicates.add(phoneJoin.in(serviceLine));
                }
                if (serviceTypes.length > 0) {
                    Join<Project, ServiceType> phoneJoin = root.join("serviceTypes");
                    predicates.add(phoneJoin.in(serviceTypes));
                }
                if (products.length > 0) {
                    Join<Project, Product> phoneJoin = root.join("product");
                    predicates.add(phoneJoin.in(products));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return proj;
    }

    public static <T> List<T> convertSetToList(Set<T> set) {
        // create an empty list
        List<T> list = new ArrayList<>();

        // push each element in the set into the list
        for (T t : set)
            list.add(t);

        // return the list
        return list;
    }

    private List<ProjectMMRDto> mmrQuarterly(List<ProjectMMR> list) {
        List<ProjectMMRDto> map = new ArrayList<ProjectMMRDto>();
        for (ProjectMMR mmr : list) {
            String key = mmr.getProject().getProjectId() + ":" + mmr.getYear();
            ProjectMMRDto dto = new ProjectMMRDto();
            dto.setProject(mmr.getProject());
            dto.setFinancialYear(mmr.getYear());
            List<ProjectMMR> projectMMRS = new ArrayList<ProjectMMR>();
            ProjectMMR Q1 = projectMMRQuaterly("Q1", mmr.getProject(), mmr.getYear(), list);
            log.info("Q1 ---------" + Q1);
            ProjectMMR Q2 = projectMMRQuaterly("Q2", mmr.getProject(), mmr.getYear(), list);
            log.info("Q2 ---------" + Q2);
            ProjectMMR Q3 = projectMMRQuaterly("Q3", mmr.getProject(), mmr.getYear(), list);
            log.info("Q3 ---------" + Q3);
            ProjectMMR Q4 = projectMMRQuaterly("Q4", mmr.getProject(), mmr.getYear(), list);
            log.info("Q4 ---------" + Q4);
            projectMMRS.add(Q1);
            projectMMRS.add(Q2);
            projectMMRS.add(Q3);
            projectMMRS.add(Q4);
            dto.setMmr(projectMMRS);
            map.add(dto);
        }

        List<ProjectMMRDto> mapNew = map
                .stream()
                .filter(util.distinctByKeys(ProjectMMRDto::getProject, ProjectMMRDto::getFinancialYear))
                .collect(Collectors.toList());
        return mapNew;
    }

    private ProjectMMR projectMMRQuaterly(String Quater, Project project, Long Year, List<ProjectMMR> list) {
        ProjectMMR map = new ProjectMMR();
        List<ProjectMMR> q1 = null;

        switch (Quater) {
            case "Q1":
                q1 = list.stream()
                        .filter(c -> ("APR".equals(c.getMonth()) || "MAY".equals(c.getMonth()) || "JUN".equals(c.getMonth()))
                                && (project.equals(c.getProject()) && Year.equals(c.getYear())))
                        .collect(Collectors.toList());
                break;
            case "Q2":
                q1 = list.stream()
                        .filter(c -> ("JUL".equals(c.getMonth()) || "AUG".equals(c.getMonth()) || "SEP".equals(c.getMonth()))
                                && (project.equals(c.getProject()) && Year.equals(c.getYear().longValue())))
                        .collect(Collectors.toList());
                break;
            case "Q3":
                q1 = list.stream()
                        .filter(c -> ("OCT".equals(c.getMonth()) || "NOV".equals(c.getMonth()) || "DEC".equals(c.getMonth()))
                                && (project.equals(c.getProject()) && Year.equals(c.getYear())))
                        .collect(Collectors.toList());
                break;
            case "Q4":
                q1 = list.stream()
                        .filter(c -> ("JAN".equals(c.getMonth()) || "FEB".equals(c.getMonth()) || "MAR".equals(c.getMonth()))
                                && (project.equals(c.getProject()) && Year.equals(c.getYear().longValue())))
                        .collect(Collectors.toList());
                break;
        }

        BigDecimal fv = BigDecimal.ZERO;
        BigDecimal bv = BigDecimal.ZERO;
        BigDecimal av = BigDecimal.ZERO;
        BigDecimal v1 = BigDecimal.ZERO;
        BigDecimal v2 = BigDecimal.ZERO;
        log.info("List : " + q1);

        for (ProjectMMR amt : q1) {
            fv = fv.add(amt.getForecastedValue());
            bv = bv.add(amt.getBudgetedValue());
            av = av.add(amt.getActualValue());
            v1 = v1.add((amt.getForecastedValue().subtract(amt.getActualValue())));
            v2 = v2.add((amt.getBudgetedValue().subtract(amt.getActualValue())));
        }

        log.info("DTO : " + Quater + ":fv " + fv + ":bv " + bv + ":av " + av);

        map.setForecastedValue(fv);
        map.setBudgetedValue(bv);
        map.setActualValue(av);
        map.setMonth(Quater);
        map.setVariance1(v1);
        map.setVariance2(v2);
        return map;
    }

    private List<ProjectMMRDto> mmrYearly(List<ProjectMMR> list) {
        List<ProjectMMRDto> map = new ArrayList<ProjectMMRDto>();
        for (ProjectMMR mmr : list) {
            String key = mmr.getProject().getProjectId() + ":" + mmr.getYear();
            ProjectMMRDto dto = new ProjectMMRDto();
            dto.setProject(mmr.getProject());
            dto.setFinancialYear(mmr.getYear());
            List<ProjectMMR> projectMMRS = new ArrayList<ProjectMMR>();
            ProjectMMR Q1 = projectMMRYearly("Year", mmr.getProject(), mmr.getYear(), list);
            log.info("Q1 ---------" + Q1);
            projectMMRS.add(Q1);
            dto.setMmr(projectMMRS);
            map.add(dto);
        }

        List<ProjectMMRDto> mapNew = map
                .stream()
                .filter(util.distinctByKeys(ProjectMMRDto::getProject, ProjectMMRDto::getFinancialYear))
                .collect(Collectors.toList());
        return mapNew;
    }

    private ProjectMMR projectMMRYearly(String Quater, Project project, Long Year, List<ProjectMMR> list) {
        ProjectMMR map = new ProjectMMR();
        List<ProjectMMR> q1 = null;

        q1 = list.stream()
                .filter(c -> (project.equals(c.getProject()) && Year.equals(c.getYear())))
                .collect(Collectors.toList());

//        q1 = list.stream()
//                .filter(c -> ("APR".equals(c.getMonth()) || "MAY".equals(c.getMonth()) || "JUN".equals(c.getMonth()))
//                        && (project.equals(c.getProject()) && Year.equals(c.getYear())))
//                .collect(Collectors.toList());

        BigDecimal fv = BigDecimal.ZERO;
        BigDecimal bv = BigDecimal.ZERO;
        BigDecimal av = BigDecimal.ZERO;
        BigDecimal v1 = BigDecimal.ZERO;
        BigDecimal v2 = BigDecimal.ZERO;
        log.info("List : " + q1);

        for (ProjectMMR amt : q1) {
            fv = fv.add(amt.getForecastedValue());
            bv = bv.add(amt.getBudgetedValue());
            av = av.add(amt.getActualValue());
            v1 = v1.add((amt.getForecastedValue().subtract(amt.getActualValue())));
            v2 = v2.add((amt.getBudgetedValue().subtract(amt.getActualValue())));
        }

        log.info("DTO : " + Quater + "fv : " + fv + "bv : " + bv + "av : " + av);

        map.setForecastedValue(fv);
        map.setBudgetedValue(bv);
        map.setActualValue(av);
        map.setMonth(Quater);
        map.setVariance1(v1);
        map.setVariance2(v2);
        return map;
    }
}
