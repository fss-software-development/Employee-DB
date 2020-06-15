package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.DuplicateRecordException;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.EmployeeSkillRepository;
import com.fss.empdb.repository.ProjectRepository;
import com.fss.empdb.util.ExceptionHandlerValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Service
public class EmployeeSkillService {

    @Value("D:/Data/Project backup/CSV PATH")
    String UPLOADED_FOLDER;

    @Autowired
    EmployeeSkillRepository employeeSkillRepository;


    public List<EmployeeSkill> getAllEmployeeSkill() throws ParseException {
        log.info("inside skill get employee");
        List <EmployeeSkill> employeeSkillList= employeeSkillRepository.findAll();
        return employeeSkillRepository.findAll();
    }

    public EmployeeSkill getEmployeeSkillById(Long employeeId) throws ParseException {
        EmployeeSkill emp= employeeSkillRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
        return emp;
    }

    public List<EmployeeSkill> findByEmpSkill(EmployeeSkill emp) {
        log.info("Employee Search Skill Service" + emp);
        return employeeSkillRepository.findAll(new Specification<EmployeeSkill>() {
            @Override
            public Predicate toPredicate(Root<EmployeeSkill> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
               /* if (!(emp.getEmployeeSkillSqId().isEmpty())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeId"), emp.getEmployeeId())));
                }*/

                if (emp.getEmployee()!= null) {
                    Join<EmployeeSkill, Employee> phoneJoin = root.join("employee");
                    predicates.add(phoneJoin.in(emp.getEmployee()));
                }
               /* if (emp.getEmployee().getEmployeeSqId()!= null) {
                    Join<EmployeeSkill, Employee> phoneJoin = root.join("employeeSqId");
                    predicates.add(phoneJoin.in(emp.getEmployee().getEmployeeSqId()));
                }*/
                if (emp.getSkill()!=null) {
                    Join<EmployeeSkill, Skill> phoneJoin = root.join("skill");
                    predicates.add(phoneJoin.in(emp.getSkill()));
                }
                /*if (emp.getSkill().getSkillName()!= null) {
                    Join<EmployeeSkill, Skill> phoneJoin = root.join("skillName");
                    predicates.add(phoneJoin.in(emp.getSkill().getSkillName()));
                }*/
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill) {
        EmployeeSkill empSave = null;
        try {
            log.info("Inside service");
            log.info("SAVE " + employeeSkill.toString());
            employeeSkill.setInsUser(Long.valueOf(1));
            employeeSkill.setInsDate(new Date());
            empSave = employeeSkillRepository.save(employeeSkill);
        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.employeeDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        }
        return empSave;
    }


//    public void deleteEmployee(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId).
//                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
//        employeeRepository.delete(employee);
//    }



}