package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.extern.log4j.Log4j2;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Log4j2
@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @Autowired
    DesignationRepository designationRepository;


    @Autowired
    LocationRepository locationRepository;


    @Autowired
    ServiceLineRepository serviceLineRepository;

    @Autowired
    BillableStatusRepository billableStatusRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    AcademicsRepository academicsRepository;

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
    }

    public List<Employee> findByEmp(EmployeeSearchCriteria emp) {
        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (emp.getEmployeeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeId"), emp.getEmployeeId())));
                }
                if (emp.getEmployeeName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("employeeName"), "%" + emp.getEmployeeName() + "%")));
                }
                if (emp.getDesignation().length > 0) {
                    Join<Employee, Designation> phoneJoin = root.join("designation");
                    predicates.add(phoneJoin.in(emp.getDesignation()));
                }
                if (emp.getDepartment().length > 0) {
                    Join<Employee, Department> phoneJoin = root.join("department");
                    predicates.add(phoneJoin.in(emp.getDepartment()));
                }
                if (emp.getRegion().length > 0) {
                    Join<Employee, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(emp.getRegion()));
                }
                if (emp.getAccount().length > 0l) {
                    Join<Employee, Account> phoneJoin = root.join("account");
                    predicates.add(phoneJoin.in(emp.getAccount()));
                }
                if (emp.getServiceLine().length > 0) {
                    Join<Employee, ServiceLine> phoneJoin = root.join("serviceLine");
                    predicates.add(phoneJoin.in(emp.getServiceLine()));
                }
                if (emp.getBillableStatus().length > 0) {
                    Join<Employee, BillableStatus> phoneJoin = root.join("billableStatus");
                    predicates.add(phoneJoin.in(emp.getBillableStatus()));
                }
                if (emp.getLocation().length > 0) {
                    Join<Employee, Location> phoneJoin = root.join("location");
                    predicates.add(phoneJoin.in(emp.getLocation()));
                }
                if (emp.getGrade().length > 0) {
                    Join<Employee, Grade> phoneJoin = root.join("grade");
                    predicates.add(phoneJoin.in(emp.getGrade()));
                }
                if (emp.getAcademics().length > 0) {
                    Join<Employee, Academics> phoneJoin = root.join("academics");
                    predicates.add(phoneJoin.in(emp.getAcademics()));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public Employee createOrUpdateEmployee(Employee employee) {
           try {
            if (employee.getEmployeeSqId() != null) {
                log.info("----------------------- Update -----------------------");
                Optional<Employee> emp = employeeRepository.findById(employee.getEmployeeSqId());
                Employee empEntity = emp.get();

                Optional<Account> account = accountRepository.findById(employee.getAccount().getAccountId());
                Account accountEntity =account.get();

                Optional<Region> region = regionRepository.findById(employee.getRegion().getRegionId());
                Region regionEntity =region.get();

                Optional<Location> location = locationRepository.findById(employee.getLocation().getLocationId());
                Location locationEntity =location.get();

                Optional<Grade> grade = gradeRepository.findById(employee.getGrade().getGradeId());
                Grade gradeEntity =grade.get();

                Optional<Designation> designation = designationRepository.findById(employee.getDesignation().getDesignationId());
                Designation designationEntity =designation.get();

                Optional<BillableStatus> billablestatus = billableStatusRepository.findById(employee.getBillableStatus().getBillableStatusId());
                BillableStatus billablestatusEntity =billablestatus.get();

                Optional<ServiceLine> serviceline = serviceLineRepository.findById(employee.getServiceLine().getServiceLineId());
                ServiceLine servicelineEntity =serviceline.get();

                Optional<Academics> academics = academicsRepository.findById(employee.getAcademics().getAcademicsId());
                Academics academicsEntity =academics.get();

                Optional<Department> department = departmentRepository.findById(employee.getDepartment().getDepartmentId());
                Department departmentEntity =department.get();

                empEntity.setEmployeeSqId(employee.getEmployeeSqId());
                empEntity.setEmployeeId(employee.getEmployeeId());
                empEntity.setDepartment(departmentEntity);
                empEntity.setAccount(accountEntity);
                empEntity.setRegion(regionEntity);
                empEntity.setLocation(locationEntity);
                empEntity.setEmployeeName(employee.getEmployeeName());
                empEntity.setMobileNum(employee.getMobileNum());
                empEntity.setEmailId(employee.getEmailId());
                empEntity.setGrade(gradeEntity);
                empEntity.setDesignation(designationEntity);
                empEntity.setReportingManager(employee.getReportingManager());
                empEntity.setPreviousExp(employee.getPreviousExp());
                empEntity.setJoiningDate(employee.getJoiningDate());
                empEntity.setBillableStatus(billablestatusEntity);
                empEntity.setServiceLine(servicelineEntity);
                empEntity.setActivityName(employee.getActivityName());
                empEntity.setPrimarySkillId(employee.getPrimarySkillId());
                empEntity.setExperienceGaps(employee.getExperienceGaps());
                empEntity.setAcademics(academicsEntity);
                empEntity.setInsUser(Long.valueOf(1));  //  Change is required
                empEntity.setInsDate( new Date());
                empEntity.setLastUpdateUser(Long.valueOf(1));  //  Change is required
                empEntity.setLastUpdateDate(new Date());
                empEntity = employeeRepository.save(empEntity);

                return empEntity;
            } else {
                log.info("----------------------- Save -----------------------");
                employee.setInsUser(Long.valueOf(1));
                employee.setLastUpdateUser(Long.valueOf(1));
                employee.setInsDate(new Date());
                employee.setLastUpdateDate(new Date());
                employee = employeeRepository.save(employee);
                return employee;
            }
        } catch (Exception e) {
            log.info(e);
        }
        return null;
    }

    public List<String> getValuesForGivenKey(JSONArray jsonArrayStr, String key) {

        log.info("In function String:" + jsonArrayStr);
        log.info("In function key " + key);

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArrayStr.length())
                .mapToObj(index -> ((JSONObject) jsonArrayStr.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
        employeeRepository.delete(employee);
    }

}
