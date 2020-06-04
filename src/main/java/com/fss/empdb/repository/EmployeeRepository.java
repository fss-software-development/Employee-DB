package com.fss.empdb.repository;

import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmployeeId(String employeeId);

//    List<Employee> findAll(Specification<Employee> employeeSpecification);

//    List<Employee> getEmployeesBySelectionCriteria(List<Employee> criteriaList);

}
