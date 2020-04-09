package com.fss.empdb.repository;

import com.fss.empdb.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long>, JpaSpecificationExecutor<Location> {

}
