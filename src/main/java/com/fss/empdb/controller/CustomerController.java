package com.fss.empdb.controller;

import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.SearchCriteria;
import com.fss.empdb.service.CustomerService;
import com.fss.empdb.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<List<Account>> getAllEmployee() {
        return ResponseEntity.ok().body(customerService.getAllAccount());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(customerService.getAccountById(employeeId));
    }

    @PostMapping(value = "/customer-search", produces = "application/json")
    public ResponseEntity<List<Account>> getAccountBySearch(String empSearch)  {
        log.info("-------Controller---------" + empSearch);


        return ResponseEntity.ok().body(customerService.findByAccount(empSearch));

    }




}
