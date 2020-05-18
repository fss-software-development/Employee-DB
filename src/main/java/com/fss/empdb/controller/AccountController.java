package com.fss.empdb.controller;

import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.AccountSearchCriteria;
import com.fss.empdb.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PreAuthorize("hasAnyAuthority('SEARCH_CUSTOMER')")
    @GetMapping("/")
    public ResponseEntity<List<Account>> allAccount() {
        return ResponseEntity.ok().body(accountService.allAccount());
    }

    @PreAuthorize("hasAnyAuthority('SEARCH_CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Account> accountById(@PathVariable(value = "id") Long accountId) {
        return ResponseEntity.ok().body(accountService.accountById(accountId));
    }

    @PreAuthorize("hasAnyAuthority('SEARCH_CUSTOMER')")
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Account>> accountsBySearch(@RequestBody AccountSearchCriteria accountSearchCriteria) {
        return ResponseEntity.ok().body(accountService.accountBySearch(accountSearchCriteria));
    }

    @PreAuthorize("hasAnyAuthority('ADD_CUSTOMER')")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.createAccount(account));
    }

    @PreAuthorize("hasAnyAuthority('EDIT_CUSTOMER')")
    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.updateAccount(account));
    }

    @PreAuthorize("hasAnyAuthority('DELETE_CUSTOMER')")
    @DeleteMapping("/{id}")
    public HttpStatus deleteAccount(@PathVariable(value = "id") Long accountId) {
        accountService.deleteAccount(accountId);
        return HttpStatus.OK;
    }

}
