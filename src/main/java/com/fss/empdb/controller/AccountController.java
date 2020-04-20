package com.fss.empdb.controller;

import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.service.AccountService;
import com.fss.empdb.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/accountservice/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    //Get All Account
    @GetMapping("/customer")
    public ResponseEntity<List<Account>> getAllAccount() {
        return ResponseEntity.ok().body(accountService.allAccount());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long accountId) {
        return ResponseEntity.ok().body(accountService.allAccountById(accountId));
    }

    @GetMapping(value = "/customer-search")
    public ResponseEntity<List<Account>> getAccountBySearch(@RequestParam(required = false) String accountName,
                                                            @RequestParam(required = false) Long[] regionId) {
        return ResponseEntity.ok().body(accountService.allAccountBySearch(accountName, regionId));
    }

    //Add & Update Account
    @PostMapping(value = "/customer-add-update")
    public ResponseEntity<Account> createOrUpdateEmployee( @RequestParam(required = false) Long accountId,
                                                          @RequestParam(required = false) String accountName,
                                                          @RequestParam(required = false) Long regionId)  {
        Account acc = accountService.createOrUpdateAccount(accountId,accountName,regionId);
        return new ResponseEntity<Account>(acc, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Account> deleteEmployee(@PathVariable(value = "id") Long accountId){
        accountService.deleteAccount(accountId);
        return new ResponseEntity<Account>(new HttpHeaders(), HttpStatus.OK);
    }

}
