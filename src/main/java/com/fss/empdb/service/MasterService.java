package com.fss.empdb.service;

import com.fss.empdb.domain.*;
import com.fss.empdb.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class MasterService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DesignationRepository designationRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ServiceLineRepository serviceLineRepository;

    @Autowired
    BillableStatusRepository billableStatusRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    AcademicsRepository academicsRepository;

    @Autowired
    ProjectTaggingRepository projectTaggingRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProjectRepository projectRepository;


    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public List<Designation> getAllDesignation() {
        return designationRepository.findAll();
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public List<Skill> getAllSkill() {
        return skillRepository.findAll();
    }

    public List<ServiceLine> getAllServiceLine() {
        return serviceLineRepository.findAll();
    }

    public List<BillableStatus> getAllBillableStatus() {
        return billableStatusRepository.findAll();
    }

    public List<Grade> getAllGrade() {
        return gradeRepository.findAll();
    }

    public List<Academics> getAllAcademics() {
        return academicsRepository.findAll();
    }

    public List<ProjectTagging> getAllProjectTagging() {
        return projectTaggingRepository.findAll();
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

}
