package com.fss.empdb.repository;

import com.fss.empdb.domain.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {
    List<Account> findAll(Specification<Account> employeeSpecification);

}
