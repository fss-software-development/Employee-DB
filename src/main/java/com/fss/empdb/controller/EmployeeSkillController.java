package com.fss.empdb.controller;

import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.EmployeeSearchCriteria;
import com.fss.empdb.domain.EmployeeSkill;
import com.fss.empdb.service.EmployeeService;
import com.fss.empdb.service.EmployeeSkillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/employeeSkills")
public class EmployeeSkillController {


    @Autowired
    EmployeeSkillService employeeSkillService;

    @PreAuthorize("hasAnyAuthority('VIEW_EMPLOYEE_SKILL')")
    @GetMapping("/")
    public ResponseEntity<List<EmployeeSkill>> getAllEmployeeSkill() throws ParseException {
        log.info("Inside get getAllEmployeeSkill");
        return ResponseEntity.ok().body(employeeSkillService.getAllEmployeeSkill());
    }

    @PreAuthorize("hasAnyAuthority('VIEW_EMPLOYEE_SKILL')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSkill> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ParseException {
        return ResponseEntity.ok().body(employeeSkillService.getEmployeeSkillById(employeeId));
    }

    @PreAuthorize("hasAnyAuthority('SEARCH_EMPLOYEE_SKILL')")
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<EmployeeSkill>> getEmployeesSkillBySearch(@RequestBody EmployeeSkill empSearch) {
        log.info("Inside get getAllEmployeeSkill search");
        return ResponseEntity.ok().body(employeeSkillService.findByEmpSkill(empSearch));
    }

    @PreAuthorize("hasAnyAuthority('ADD_EMPLOYEE_SKILL')")
    @PostMapping
    public ResponseEntity<EmployeeSkill> createEmployeeSkill(@RequestBody EmployeeSkill employeeSkill) {
        log.info("Inside controller");
        return ResponseEntity.ok().body(employeeSkillService.createEmployeeSkill(employeeSkill));
    }
}
