package com.fss.empdb.repository;

import com.fss.empdb.domain.Skill;
import com.fss.empdb.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatusRepository extends JpaRepository<Status,Long>, JpaSpecificationExecutor<Status> {

}