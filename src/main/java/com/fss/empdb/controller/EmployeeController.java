package com.fss.empdb.controller;

import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.EmployeeSearchCriteria;
import com.fss.empdb.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @PreAuthorize("hasAnyAuthority('VIEW_EMPLOYEE')")
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee() throws ParseException {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @PreAuthorize("hasAnyAuthority('VIEW_EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ParseException {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    @PreAuthorize("hasAnyAuthority('SEARCH_EMPLOYEE')")
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeesBySearch(@RequestBody EmployeeSearchCriteria empSearch) {
        return ResponseEntity.ok().body(employeeService.findByEmp(empSearch));
    }

    @PreAuthorize("hasAnyAuthority('ADD_EMPLOYEE')")
    @PostMapping
    public ResponseEntity<Employee> createAccount(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.createEmployee(employee));
    }

    @PreAuthorize("hasAnyAuthority('EDIT_EMPLOYEE')")
    @PutMapping
    public ResponseEntity<Employee> updateAccount(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(employee));
    }

    @PreAuthorize("hasAnyAuthority('DELETE_EMPLOYEE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<Employee>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile) {

        log.info("Single file upload! " + uploadfile.getOriginalFilename());

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.BAD_REQUEST);
        }

        try {

            employeeService.saveUploadedFiles(Arrays.asList(uploadfile));

        } catch (IOException e) {
            log.error("Employee File Upload error:",e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

}
