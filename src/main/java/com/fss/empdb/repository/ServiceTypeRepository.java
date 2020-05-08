package com.fss.empdb.repository;

import com.fss.empdb.domain.ServiceLine;
import com.fss.empdb.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceTypeRepository extends JpaRepository<ServiceType,Long>, JpaSpecificationExecutor<ServiceType> {

}