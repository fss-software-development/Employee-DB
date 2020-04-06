package com.fss.empdb.service;

import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.repository.EmployeeRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static Logger log = Logger.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public List<Employee> findByCriteria(Long employeeCode,
                                         String employeeName,
                                         Long designationId,
                                         Long departmentId,
                                         Long regionId,
                                         Long accountId,
                                         Long serviceLineId,
                                         Long billableStatusId,
                                         Long projectId,
                                         Long locationId,
                                         Long gradeId,
                                         Long academicId,
                                         String projectTagging
    ) {

        log.info("------------------Search Field-----------------");
        log.info("employeeCode : " + employeeCode);
        log.info("employeeName : " + employeeName);
        log.info("designationId : " + designationId);
        log.info("departmentId : " + departmentId);
        log.info("regionId : " + regionId);
        log.info("accountId : " + accountId);
        log.info("serviceLineId : " + serviceLineId);
        log.info("billableStatusId : " + billableStatusId);
        log.info("projectId : " + projectId);
        log.info("locationId : " + locationId);
        log.info("gradeId : " + gradeId);
        log.info("academicId : " + academicId);
        log.info("projectTagging : " + projectTagging);
        log.info("------------------------------------------------");
        return employeeRepository.findAll(new Specification<Employee>() {

            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (employeeCode != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("empCode"), employeeCode)));
                }
                if (!(employeeName.equals("null"))) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("empName"), "%" + employeeName + "%")));
                }
                if (designationId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("designationId"), designationId)));
                }
                if (departmentId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("departmentId"), departmentId)));
                }
                if (regionId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("regionId"), regionId)));
                }
                if (accountId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("accountId"), accountId)));
                }
                if (serviceLineId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("serviceLineId"), serviceLineId)));
                }
                if (billableStatusId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("billableStatusId"), billableStatusId)));
                }
                if (projectId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("projectId"), projectId)));
                }
                if (locationId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("locationId"), locationId)));
                }
                if (gradeId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gradeId"), gradeId)));
                }
                if (academicId != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("academicId"), academicId)));
                }
                if (!(projectTagging.equals("null"))) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("projectTagging"), projectTagging)));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

}
