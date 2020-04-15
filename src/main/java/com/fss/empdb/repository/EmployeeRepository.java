package com.fss.empdb.repository;

import com.fss.empdb.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

//    List<Employee> findAll(Specification<Employee> employeeSpecification);

//    List<Employee> getEmployeesBySelectionCriteria(List<Employee> criteriaList);

}
