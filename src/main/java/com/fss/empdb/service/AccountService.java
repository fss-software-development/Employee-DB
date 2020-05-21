package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.DuplicateRecordException;
import com.fss.empdb.exception.GlobalExceptionHandler;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.AccountRepository;
import com.fss.empdb.repository.RegionRepository;
import com.fss.empdb.util.ExceptionHandlerValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Account accountById(Long accountId) {
        return accountRepository.findById(accountId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND + accountId));
    }

    public List<Account> accountBySearch(AccountSearchCriteria accountSearchCriteria) {
        List<Account> acc = null;
        try {
            acc = accountRepository.findAll(new Specification<Account>() {
                @Override
                public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();
                    if (accountSearchCriteria.getAccountName() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("accountName"), "%" + accountSearchCriteria.getAccountName() + "%")));
                    }
                    if (accountSearchCriteria.getRegion().length > 0) {
                        Join<Account, Region> phoneJoin = root.join("region");
                        predicates.add(phoneJoin.in(accountSearchCriteria.getRegion()));
                    }
                    log.info("Search filter Size :" + predicates.size());
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            });
        } catch (Exception e) {
            log.error("ERROR_LOG" + e);
        }
        return acc;
    }

    public Account createAccount(Account account) {
        Account saveAccount = null;
        try {
            account.setInsUser(Long.valueOf(1));
            account.setLastUpdateUser(Long.valueOf(1));
            account.setInsDate(new Date());
            account.setLastUpdateDate(new Date());
            saveAccount = accountRepository.save(account);
        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.accountDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        } catch (NullPointerException ex) {
            log.error("null record", ex);
            //   throw new MethodArgumentNotValidException();
        }
        return saveAccount;
    }

    public Account updateAccount(Account account) {

        Account updateAccount = null;
        try {
            Optional<Account> acc = accountRepository.findById(account.getAccountId());
            Account accEntity = acc.get();
            account.setInsUser(accEntity.getInsUser());
            account.setLastUpdateUser(Long.valueOf(1));
            account.setInsDate(accEntity.getInsDate());
            account.setLastUpdateDate(new Date());
            updateAccount = accountRepository.save(account);
        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.accountDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        } catch (NullPointerException ex) {
            log.error("null record", ex);
            //   throw new MethodArgumentNotValidException();
        }
        return updateAccount;
    }

    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.CUSTOMER_NOT_FOUND + accountRepository));
        accountRepository.delete(account);
    }
}
