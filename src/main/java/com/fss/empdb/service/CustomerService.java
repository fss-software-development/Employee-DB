package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.AccountRepository;
import com.fss.empdb.repository.EmployeeRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerService {
    private static Logger log = Logger.getLogger(CustomerService.class);

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccount() {

        return accountRepository.findAll();
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + accountId));
    }

    public List<Account> findByAccount(String searchName) {

        return accountRepository.findAll(new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                log.info("Predicate class----------------------");
                List<Predicate> predicates = new ArrayList<>();

                if (searchName != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("accountName"), "%" + searchName+ "%")));
                }

                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
