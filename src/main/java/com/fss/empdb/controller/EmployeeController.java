package com.fss.empdb.controller;

import com.fss.empdb.domain.Employee;
import com.fss.empdb.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/services")
public class EmployeeController {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get-all-employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/get-all-employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    @RequestMapping(value = "/emp-search-criteria", method = RequestMethod.POST)
    public ResponseEntity<List<Employee>> getEmployeeBySearchCriteria1(@RequestBody Employee empSearch) {
        return  ResponseEntity.ok().body(employeeService.findByEmp(empSearch));
    }

}
