package com.fss.empdb.controller;

import com.fss.empdb.domain.Employee;
import com.fss.empdb.service.EmployeeService;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get-all-employee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get-all-employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/search-criteria/{empCode}/{empName}/{designationId}/{departmentId}/{regionId}/{accountId}" +
            "/{serviceLineId}/{billableStatusId}/{projectId}/{locationId}/{gradeId}/{academicId}/{projectTagging}")
    public List<Employee> getEmployeeBySearchCriteria(@PathVariable(value = "empCode") Long employeeCode,
                                                      @PathVariable(value = "empName") String employeeName,
                                                      @PathVariable(value = "designationId") Long designationId,
                                                      @PathVariable(value = "departmentId") Long departmentId,
                                                      @PathVariable(value = "regionId") Long regionId,
                                                      @PathVariable(value = "accountId") Long accountId,
                                                      @PathVariable(value = "serviceLineId") Long serviceLineId,
                                                      @PathVariable(value = "billableStatusId") Long billableStatusId,
                                                      @PathVariable(value = "projectId") Long projectId,
                                                      @PathVariable(value = "locationId") Long locationId,
                                                      @PathVariable(value = "gradeId") Long gradeId,
                                                      @PathVariable(value = "academicId") Long academicId,
                                                      @PathVariable(value = "projectTagging") String projectTagging){

        return  employeeService.findByCriteria(employeeCode,employeeName,designationId,departmentId,regionId,accountId,
                serviceLineId,billableStatusId,projectId,locationId,gradeId,academicId,projectTagging);

    }



}
