package com.fss.empdb.repository;

import com.fss.empdb.domain.BillableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BillableStatusRepository extends JpaRepository<BillableStatus,Long>, JpaSpecificationExecutor<BillableStatus> {

}
