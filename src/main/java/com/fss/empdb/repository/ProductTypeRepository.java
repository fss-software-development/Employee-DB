package com.fss.empdb.repository;

import com.fss.empdb.domain.ProductType;
import com.fss.empdb.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeRepository  extends JpaRepository<ProductType,Long>, JpaSpecificationExecutor<ProductType> {

}
