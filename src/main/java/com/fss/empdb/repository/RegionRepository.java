package com.fss.empdb.repository;

import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>, JpaSpecificationExecutor<Region> {

    @Query(value = "SELECT u FROM Region u WHERE u.regionId IN :regionId")
    List<Region> findAllAccountByRegion(@Param("regionId") Long[] regionId);
}
