package com.fss.empdb.repository;

import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long>, JpaSpecificationExecutor<EmployeeSkill> {

//    Optional<Employee> findByEmployeeId(String employeeId);


}
