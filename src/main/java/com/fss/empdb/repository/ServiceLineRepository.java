package com.fss.empdb.repository;

import com.fss.empdb.domain.ServiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLineRepository extends JpaRepository<ServiceLine,Long>, JpaSpecificationExecutor<ServiceLine> {

}
