package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.AccountRepository;
import com.fss.empdb.repository.RegionRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static Logger log = Logger.getLogger(CustomerService.class);

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

    public Account createOrUpdateAccount(Long accountId, String accountName, Long regionId) {

        Account accEntity = new Account();
        Optional<Region> region = regionRepository.findById(regionId);
        Region regionEntity = region.get();

        try {
            if (accountId != null) {
                log.info("----------------------- Update -----------------------");
                Optional<Account> acc = accountRepository.findById(accountId);
                accEntity = acc.get();
                accEntity.setAccountName(accountName);
                accEntity.setRegion(regionEntity);
                accEntity.setInsDate(new Date());
                accEntity.setLastUpdateUser(Long.valueOf(1));  //  Change is required
                accEntity.setLastUpdateDate(new Date());
                accEntity = accountRepository.save(accEntity);
                return accEntity;
            } else {
                log.info("----------------------- Save -----------------------");
                accEntity.setAccountName(accountName);
                accEntity.setRegion(regionEntity);
                accEntity.setInsUser(Long.valueOf(1));
                accEntity.setLastUpdateUser(Long.valueOf(1));
                accEntity.setInsDate(new Date());
                accEntity.setLastUpdateDate(new Date());
                accEntity = accountRepository.save(accEntity);
                return accEntity;
            }
        } catch (Exception e) {
            log.info(e);
        }
        return null;
    }

    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND + accountRepository));
        accountRepository.delete(account);
    }
}
