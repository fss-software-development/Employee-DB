package com.fss.empdb.controller;

import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.EmployeeSearchCriteria;
import com.fss.empdb.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeesBySearch(@RequestBody EmployeeSearchCriteria empSearch)  {
        return ResponseEntity.ok().body(employeeService.findByEmp(empSearch));
    }

    //Add & Update Employee
    @PostMapping(value = "/emp-add-update")
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee)  {
        return ResponseEntity.ok().body(employeeService.createOrUpdateEmployee(employee));
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<Employee>(new HttpHeaders(), HttpStatus.OK);
    }

}
