package com.fss.empdb.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.SearchCriteria;
import com.fss.empdb.service.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.api.scripting.ScriptUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/empservice/v1")
public class EmployeeController {

    //private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    //Get All Employee
    @GetMapping("/get-all-employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    //Get Employee Details By Id - View Case
    @GetMapping("/get-all-employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    //Get Employees By Search Criteria
    @PostMapping(value = "/emp-search-criteria", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeeBySearchCriteria1(@RequestBody SearchCriteria empSearch)  {
        log.info("-------getEmployeeBySearchCriteria1---------" + empSearch);
        //LOGGER.info("-------Controller---------" + empSearch);
        return ResponseEntity.ok().body(employeeService.findByEmp(empSearch));
    }

    //Add & Update Employee
    @PostMapping(value = "/emp-add-update")
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee)  {
        log.info("-------createOrUpdateEmployee---------" + employee);
        Employee emp = employeeService.createOrUpdateEmployee(employee);
        return new ResponseEntity<Employee>(emp, new HttpHeaders(), HttpStatus.OK);
    }

    //Delete Employee
    @DeleteMapping(value = "/emp-delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId){
        log.info("-------deleteEmployee---------" + employeeId);
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<Employee>(new HttpHeaders(), HttpStatus.OK);
    }

}
