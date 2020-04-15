package com.fss.empdb.service;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.EmployeeRepository;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Employee> findByEmp(Employee employee) {
        log.info("Service class----------------------");
        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                log.info("Predicate class----------------------");
                List<Predicate> predicates = new ArrayList<>();
                log.info("getEmployeeSqId class----------------------" + employee.getEmployeeSqId());
                log.info("getEmployeeId class----------------------" + employee.getEmployeeId());
                log.info("getEmployeeName class----------------------" + employee.getEmployeeName());
                if (employee.getEmployeeSqId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeSqId"), employee.getEmployeeSqId())));
                }
                if (employee.getEmployeeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeId"), employee.getEmployeeId())));
                }
                if (employee.getEmployeeName() != null) {
                    log.info("-------------------------employeeName" + employee.getEmployeeName().toString());
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("employeeName"), "%" + employee.getEmployeeName() + "%")));
                } else {
                    log.info("----------------else--------employeeName" + employee.getEmployeeName().toString());

                }
                if ((employee.getDesignation() != null)) {
                    log.info("-------------------------getDesignation" + employee.getDesignation().toString());
                    Join<Employee, Designation> phoneJoin = root.join("designation");
                    predicates.add(phoneJoin.in(employee.getDesignation()));
                }
//                if (!(employee.getDepartment().isEmpty())) {
//                    log.info("-------------------------Department" + employee.getDepartment().toString());
//                    Join<Employee, Department> phoneJoin = root.join("department");
//                    predicates.add(phoneJoin.in(employee.getDepartment()));
//                }
//                if (!(employee.getRegion().isEmpty())) {
//                    Join<Employee, Region> phoneJoin = root.join("region");
//                    predicates.add(phoneJoin.in(employee.getRegion()));
//                }
//                if (!(employee.getAccount().isEmpty())) {
//                    Join<Employee, Account> phoneJoin = root.join("account");
//                    predicates.add(phoneJoin.in(employee.getAccount()));
//                }
//                if (!(employee.getServiceLine().isEmpty())) {
//                    Join<Employee, ServiceLine> phoneJoin = root.join("serviceLine");
//                    predicates.add(phoneJoin.in(employee.getServiceLine()));
//                }
//
//                if (!(employee.getBillableStatus().isEmpty())) {
//                    Join<Employee, BillableStatus> phoneJoin = root.join("billableStatus");
//                    predicates.add(phoneJoin.in(employee.getBillableStatus()));
//                }
//
//                if (!(employee.getLocation().isEmpty())) {
//                    Join<Employee, Location> phoneJoin = root.join("location");
//                    predicates.add(phoneJoin.in(employee.getLocation()));
//                }
//                if (!(employee.getGrade().isEmpty())) {
//                    Join<Employee, Grade> phoneJoin = root.join("grade");
//                    predicates.add(phoneJoin.in(employee.getGrade()));
//                }
//                if (!(employee.getAcademics().isEmpty())) {
//                    Join<Employee, Region> phoneJoin = root.join("academics");
//                    predicates.add(phoneJoin.in(employee.getAcademics()));
//                }


//                if (employee.getAccountId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("accountId"), employee.getAccountId())));
//                }
//                if (employee.getServiceLineId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("serviceLineId"), employee.getServiceLineId())));
//                }
//                if (employee.getBillableStatusId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("billableStatusId"), employee.getBillableStatusId())));
//                }
//               /* if (employee.getProjectId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("projectId"), employee.getProjectId())));
//                }*/
//                if (employee.getLocationId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("locationId"), employee.getLocationId())));
//                }
//                if (employee.getGradeId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gradeId"), employee.getGradeId())));
//                }
//                if (employee.getAcademicId() != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("academicId"), employee.getAcademicId())));
//                }
               /* if (!(employee.getProjectTaggingId().equals("null"))) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("projectTagging"), employee.getProjectTaggingId())));
                }*/
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public Employee createOrUpdateEmployee(Employee employee) {

//        log.info("------------------Add Update Field-----------------");
//        log.info("employee Sq Id : " + employee.getEmployeeSqId());
//        log.info("employee Id : " + employee.getEmployeeId());
//        log.info("employeeName : " + employee.getFirstName());
//        log.info("designationId : " + employee.getDesignationId());
//        log.info("departmentId : " + employee.getDepartmentId());
//        log.info("regionId : " + employee.getRegionId());
//        log.info("accountId : " + employee.getAccountId());
//        log.info("serviceLineId : " + employee.getServiceLineId());
//        log.info("billableStatusId : " + employee.getBillableStatusId());
//        // log.info("projectId : " + employee.getProjectId());
//        log.info("locationId : " + employee.getLocationId());
//        log.info("gradeId : " + employee.getGradeId());
//        log.info("academicId : " + employee.getAcademicId());
//        //log.info("projectTagging : " + employee.getProjectTagging());
//
//        if (employee.getEmployeeSqId() != null) {
//            log.info("----------------------- Update -----------------------");
//            Optional<Employee> emp = employeeRepository.findById(employee.getEmployeeSqId());
//            Employee empEntity = emp.get();
//            empEntity.setEmployeeSqId(employee.getEmployeeSqId());
//            empEntity.setEmployeeId(employee.getEmployeeId());
//            empEntity.setDepartmentId(employee.getDepartmentId());
//            empEntity.setAccountId(employee.getAccountId());
//            empEntity.setRegionId(employee.getRegionId());
//            empEntity.setLocationId(employee.getLocationId());
//            empEntity.setEmployeeName(employee.getEmployeeName());
//            empEntity.setMobileNum(employee.getMobileNum());
//            empEntity.setEmailId(employee.getEmailId());
//            empEntity.setGradeId(employee.getGradeId());
//            empEntity.setDesignationId(employee.getDesignationId());
//            empEntity.setReportingManager(employee.getReportingManager());
//            empEntity.setPreviousExp(employee.getPreviousExp());
//            empEntity.setJoiningDate(employee.getJoiningDate());
//            empEntity.setBillableStatusId(employee.getBillableStatusId());
//            empEntity.setServiceLineId(employee.getServiceLineId());
//            empEntity.setActivityName(employee.getActivityName());
//            empEntity.setPrimarySkillId(employee.getPrimarySkillId());
//            empEntity.setExperienceGaps(employee.getExperienceGaps());
//            empEntity.setAcademicId(employee.getAcademicId());
//            empEntity.setInsUser(Long.valueOf(1));  //  Change is required
//            empEntity.setInsDate(employee.getInsDate());
//            empEntity.setLastUpdateUser(Long.valueOf(1));  //  Change is required
//            empEntity.setLastUpdateDate(employee.getLastUpdateDate());
//            empEntity = employeeRepository.save(empEntity);

//            return empEntity;
//        } else {
//            employee = employeeRepository.save(employee);
//            return employee;
//        }
        return null;
    }

    public List<String> getValuesForGivenKey(JSONArray jsonArrayStr, String key) {

        log.info("In function String:" + jsonArrayStr);
        log.info("In function key " + key);

//        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArrayStr.length())
                .mapToObj(index -> ((JSONObject) jsonArrayStr.get(index)).optString(key))
                .collect(Collectors.toList());
    }

}
