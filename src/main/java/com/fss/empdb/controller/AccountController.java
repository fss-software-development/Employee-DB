package com.fss.empdb.controller;

import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.AccountSearchCriteria;
import com.fss.empdb.domain.ProjectCriteria;
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
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<Account>> allAccount() {
        return ResponseEntity.ok().body(accountService.allAccount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> allAccountById(@PathVariable(value = "id") Long accountId) {
        return ResponseEntity.ok().body(accountService.allAccountById(accountId));
    }

    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Account>> getAccountsBySearch(@RequestBody AccountSearchCriteria accountSearchCriteria) {
        return ResponseEntity.ok().body(accountService.allAccountBySearch(accountSearchCriteria));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.createAccount(account));
    }

    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.updateAccount(account));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAccount(@PathVariable(value = "id") Long accountId) {
        accountService.deleteAccount(accountId);
        return HttpStatus.OK;
    }

}
