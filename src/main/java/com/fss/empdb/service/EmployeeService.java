package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.EmployeeRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    private static Logger log = Logger.getLogger(EmployeeController.class);


    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
    }

    public List<Employee> findByEmp(SearchCriteria emp) {


//        Collection<Department> dep = (Collection<Department>) emp.getDepartment();
//        log.info("Collection<Department> ----------------------" + dep);

        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                log.info("Predicate class----------------------");
                List<Predicate> predicates = new ArrayList<>();

                if (emp.getEmployeeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeId"), emp.getEmployeeId())));
                }
                if (emp.getEmployeeName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("employeeName"), "%" + emp.getEmployeeName() + "%")));
                }
                if (emp.getDesignation() != null) {
                    Join<Employee, Designation> phoneJoin = root.join("designation");
                    predicates.add(phoneJoin.in(emp.getDesignation()));
                }
                if (emp.getDepartment() != null) {
                    Join<Employee, Department> phoneJoin = root.join("department");
                    predicates.add(phoneJoin.in(emp.getDepartment()));
                }
                if (emp.getRegion() != null) {
                    Join<Employee, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(emp.getRegion()));
                }
                if (emp.getAccount() != null) {
                    Join<Employee, Account> phoneJoin = root.join("account");
                    predicates.add(phoneJoin.in(emp.getAccount()));
                }
                if (emp.getServiceLine() != null) {
                    Join<Employee, ServiceLine> phoneJoin = root.join("serviceLine");
                    predicates.add(phoneJoin.in(emp.getServiceLine()));
                }
                if (emp.getBillableStatus() != null) {
                    Join<Employee, BillableStatus> phoneJoin = root.join("billableStatus");
                    predicates.add(phoneJoin.in(emp.getBillableStatus()));
                }
                if (emp.getLocation() != null) {
                    Join<Employee, Location> phoneJoin = root.join("location");
                    predicates.add(phoneJoin.in(emp.getLocation()));
                }
                if (emp.getGrade() != null) {
                    Join<Employee, Grade> phoneJoin = root.join("grade");
                    predicates.add(phoneJoin.in(emp.getGrade()));
                }
                if (emp.getAcademics() != null) {
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
                empEntity.setEmployeeSqId(employee.getEmployeeSqId());
                empEntity.setEmployeeId(employee.getEmployeeId());
                empEntity.setDepartment(employee.getDepartment());
                empEntity.setAccount(employee.getAccount());
                empEntity.setRegion(employee.getRegion());
                empEntity.setLocation(employee.getLocation());
                empEntity.setEmployeeName(employee.getEmployeeName());
                empEntity.setMobileNum(employee.getMobileNum());
                empEntity.setEmailId(employee.getEmailId());
                empEntity.setGrade(employee.getGrade());
                empEntity.setDesignation(employee.getDesignation());
                empEntity.setReportingManager(employee.getReportingManager());
                empEntity.setPreviousExp(employee.getPreviousExp());
                empEntity.setJoiningDate(employee.getJoiningDate());
                empEntity.setBillableStatus(employee.getBillableStatus());
                empEntity.setServiceLine(employee.getServiceLine());
                empEntity.setActivityName(employee.getActivityName());
                empEntity.setPrimarySkillId(employee.getPrimarySkillId());
                empEntity.setExperienceGaps(employee.getExperienceGaps());
                empEntity.setAcademics(employee.getAcademics());
                empEntity.setInsUser(Long.valueOf(1));  //  Change is required
                empEntity.setInsDate(new Date(119,6,38));
                empEntity.setLastUpdateUser(Long.valueOf(1));  //  Change is required
                empEntity.setLastUpdateDate(new Timestamp(new Date().getTime()));
                empEntity = employeeRepository.save(empEntity);

                return empEntity;
            } else {
                log.info("----------------------- Save -----------------------");
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

}
