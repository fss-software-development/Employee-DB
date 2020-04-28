package com.fss.empdb.repository;

import com.fss.empdb.domain.Skill;
import com.fss.empdb.domain.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ToolsRepository extends JpaRepository<Tools,Long>, JpaSpecificationExecutor<Tools> {
}
