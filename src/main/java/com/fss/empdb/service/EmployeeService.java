package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.EmployeeRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        log.info("------------------Search Field-----------------");
        log.info("employee Sq Id : " + employee.getEmployeeSqId());
        log.info("employeeCode : " + employee.getEmployeeId());
        log.info("employeeName : " + employee.getFirstName());
        log.info("designationId : " + employee.getDesignationId());
        log.info("departmentId : " + employee.getDepartmentId());
        log.info("regionId : " + employee.getRegionId());
        log.info("accountId : " + employee.getAccountId());
        log.info("serviceLineId : " + employee.getServiceLineId());
        log.info("billableStatusId : " + employee.getBillableStatusId());
        // log.info("projectId : " + employee.getProjectId());
        log.info("locationId : " + employee.getLocationId());
        log.info("gradeId : " + employee.getGradeId());
        log.info("academicId : " + employee.getAcademicId());
        //log.info("projectTagging : " + employee.getProjectTagging());
        log.info("------------------------------------------------");
        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (employee.getEmployeeSqId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeSqId"), employee.getEmployeeSqId())));
                }
                if (employee.getEmployeeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeId"), employee.getEmployeeId())));
                }
                if (!(employee.getFirstName().equals("null"))) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("firstName"), "%" + employee.getFirstName() + "%")));
                }
                if (employee.getDesignationId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("designationId"), employee.getDesignationId())));
                }
                if (employee.getDepartmentId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("departmentId"), employee.getDepartmentId())));
                }
                if (employee.getRegionId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("regionId"), employee.getRegionId())));
                }
                if (employee.getAccountId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("accountId"), employee.getAccountId())));
                }
                if (employee.getServiceLineId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("serviceLineId"), employee.getServiceLineId())));
                }
                if (employee.getBillableStatusId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("billableStatusId"), employee.getBillableStatusId())));
                }
               /* if (employee.getProjectId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("projectId"), employee.getProjectId())));
                }*/
                if (employee.getLocationId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("locationId"), employee.getLocationId())));
                }
                if (employee.getGradeId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gradeId"), employee.getGradeId())));
                }
                if (employee.getAcademicId() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("academicId"), employee.getAcademicId())));
                }
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

        if (employee.getEmployeeSqId() != null) {
            log.info("----------------------- Update -----------------------");
            Optional<Employee> emp = employeeRepository.findById(employee.getEmployeeSqId());
            Employee empEntity = emp.get();
            empEntity.setEmployeeSqId(employee.getEmployeeSqId());
            empEntity.setEmployeeId(employee.getEmployeeId());
            empEntity.setDepartmentId(employee.getDepartmentId());
            empEntity.setAccountId(employee.getAccountId());
            empEntity.setRegionId(employee.getRegionId());
            empEntity.setLocationId(employee.getLocationId());
            empEntity.setFirstName(employee.getFirstName());
            empEntity.setMiddleName(employee.getMiddleName());
            empEntity.setLastName(employee.getLastName());
            empEntity.setMobileNum(employee.getMobileNum());
            empEntity.setEmailId(employee.getEmailId());
            empEntity.setGradeId(employee.getGradeId());
            empEntity.setDesignationId(employee.getDesignationId());
            empEntity.setReportingManager(employee.getReportingManager());
            empEntity.setPreviousExp(employee.getPreviousExp());
            empEntity.setJoiningDate(employee.getJoiningDate());
            empEntity.setBillableStatusId(employee.getBillableStatusId());
            empEntity.setServiceLineId(employee.getServiceLineId());
            empEntity.setActivityName(employee.getActivityName());
            empEntity.setPrimarySkillId(employee.getPrimarySkillId());
            empEntity.setExperienceGaps(employee.getExperienceGaps());
            empEntity.setAcademicId(employee.getAcademicId());
            empEntity.setProjectTaggingId(employee.getProjectTaggingId()); //  Change is required
            empEntity.setRoleId(employee.getRoleId());
            empEntity.setInsUser(Long.valueOf(1));  //  Change is required
            empEntity.setInsDate(employee.getInsDate());
            empEntity.setLastUpdateUser(Long.valueOf(1));  //  Change is required
            empEntity.setLastUpdateDate(employee.getLastUpdateDate());
            empEntity = employeeRepository.save(empEntity);

            return empEntity;
        } else {
            employee = employeeRepository.save(employee);
            return employee;
        }
    }

}
