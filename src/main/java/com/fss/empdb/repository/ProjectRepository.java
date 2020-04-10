package com.fss.empdb.repository;

import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.ProjectTagging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {
}
