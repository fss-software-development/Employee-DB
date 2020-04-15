package com.fss.empdb.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/services")
public class EmployeeController {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    //Get All Employee
    @PostMapping("/get-all-employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        logger.info(" Started get-all-employee :");
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    //Get Employee Details By Id - View Case
    @GetMapping("/get-all-employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    //Get Employees By Search Criteria

    @RequestMapping(value = "/emp-search-criteria", method = RequestMethod.POST)
    public ResponseEntity<List<Employee>> getEmployeeBySearchCriteria1(@RequestBody Employee empSearch) {
        LOGGER.info("Employee Controller" + empSearch.toString());
        return ResponseEntity.ok().body(employeeService.findByEmp(empSearch));
    }

//
//    //Add & Update Employee
//    @PostMapping(value = "/emp-add-update")
//    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee)  {
//        Employee emp = employeeService.createOrUpdateEmployee(employee);
//        return new ResponseEntity<Employee>(emp, new HttpHeaders(), HttpStatus.OK);
//    }

}
