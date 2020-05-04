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


    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
    }

    public List<Employee> findByEmp(EmployeeSearchCriteria emp) {
        log.info("Employee Search Service" + emp);
        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(emp.getEmployeeId().isEmpty())) {
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
                    log.info("Employee Search Account" + emp.getAccount());
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
                if (emp.getProjects().length > 0) {
                    Join<Employee, Project> phoneJoin = root.join("projects");
                    predicates.add(phoneJoin.in(emp.getProjects()));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public Employee createEmployee(Employee employee) {

        log.info("SAVE " + employee.toString());
        employee.setInsUser(Long.valueOf(1));
        employee.setLastUpdateUser(Long.valueOf(1));
        employee.setInsDate(new Date());
        employee.setLastUpdateDate(new Date());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {

        Optional<Employee> emp =employeeRepository.findById(employee.getEmployeeSqId());
        Employee empEntity=emp.get();
        employee.setInsUser(empEntity.getInsUser());
        employee.setLastUpdateUser(Long.valueOf(1));
        employee.setInsDate(empEntity.getInsDate());
        employee.setLastUpdateDate(new Date());
        return employeeRepository.save(employee);
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
