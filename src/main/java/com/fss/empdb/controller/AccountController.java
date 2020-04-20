package com.fss.empdb.controller;

import com.fss.empdb.domain.Account;
import com.fss.empdb.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccount() {
        return ResponseEntity.ok().body(accountService.allAccount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long accountId) {
        return ResponseEntity.ok().body(accountService.allAccountById(accountId));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<Account>> getAccountBySearch(@RequestParam(required = false) String accountName,
                                                            @RequestParam(required = false) Long[] regionId) {
        log.info("account name" +accountName);
        log.info("regionId" +regionId.toString());
        return ResponseEntity.ok().body(accountService.allAccountBySearch(accountName, regionId));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.createAccount(account));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        return ResponseEntity.ok().body(accountService.updateAccount(account));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable(value = "id") Long accountId){
        accountService.deleteAccount(accountId);
        return new ResponseEntity<Account>(new HttpHeaders(), HttpStatus.OK);
    }

}
