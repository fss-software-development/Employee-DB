package com.fss.empdb.repository;

import com.fss.empdb.domain.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Long>, JpaSpecificationExecutor<Designation> {

}
