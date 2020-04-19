package com.fss.empdb.repository;

import com.fss.empdb.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {

    public Project findByProjectName(String projectName);

   // public List<Project> findByProjectName(String projectName);
}
