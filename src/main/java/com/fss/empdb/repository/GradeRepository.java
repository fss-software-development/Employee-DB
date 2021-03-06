package com.fss.empdb.repository;

import com.fss.empdb.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long>, JpaSpecificationExecutor<Grade> {

}
