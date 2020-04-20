package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.AccountRepository;
import com.fss.empdb.repository.RegionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegionRepository regionRepository;

    public List<Account> allAccount() {
        return accountRepository.findAll();
    }

    public Account allAccountById(Long accountId) {
        return accountRepository.findById(accountId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND + accountId));
    }

    public List<Account> allAccountBySearch(String accountName, Long[] regionId) {

        List<Account> accounts = null;
        List<Region> region = null;

        if (regionId != null) {
            region = regionRepository.findAllAccountByRegion(regionId);
        }

        List<Region> finalRegion = region;
        accounts = accountRepository.findAll(new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (accountName != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("accountName"), "%" + accountName + "%")));
                }
                if (finalRegion != null) {
                    Join<Account, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(finalRegion));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return accounts;
    }

    public Account createAccount(Account account) {
        account.setInsUser(Long.valueOf(1));
        account.setLastUpdateUser(Long.valueOf(1));
//        account.setInsDate(new Date());
//        account.setLastUpdateDate(new Date());
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account){
        account.setInsUser(Long.valueOf(1));
        account.setLastUpdateUser(Long.valueOf(1));
        account.setInsDate(new Date());
        account.setLastUpdateDate(new Date());
        accountRepository.findById(account.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND
                        + account.getAccountId()));


        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND + accountRepository));
        accountRepository.delete(account);
    }
}
