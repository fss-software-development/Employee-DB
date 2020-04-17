package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.controller.EmployeeController;
import com.fss.empdb.domain.*;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.*;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProjectService {

    private static Logger log = Logger.getLogger(EmployeeController.class);


    @Autowired
    EmployeeRepository projectRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @Autowired
    DesignationRepository designationRepository;


    @Autowired
    LocationRepository locationRepository;


    @Autowired
    ServiceLineRepository serviceLineRepository;

    @Autowired
    BillableStatusRepository billableStatusRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    AcademicsRepository academicsRepository;

    public List<Employee> getAllProject() {

        return projectRepository.findAll();
    }

}
