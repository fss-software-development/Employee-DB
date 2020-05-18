package com.fss.empdb.repository;


import com.fss.empdb.domain.ProductType;
import com.fss.empdb.domain.ProjectMMR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectMmrRepository extends JpaRepository<ProjectMMR,Long>, JpaSpecificationExecutor<ProjectMMR> {

}

