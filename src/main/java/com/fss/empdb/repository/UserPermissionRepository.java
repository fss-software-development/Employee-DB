package com.fss.empdb.repository;

import com.fss.empdb.domain.BillableStatus;
import com.fss.empdb.domain.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission,Long>, JpaSpecificationExecutor<UserPermission> {

}
