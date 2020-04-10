package com.fss.empdb.controller;

import com.fss.empdb.domain.*;
import com.fss.empdb.service.MasterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping("/master/get-all-department")
    public List<Department> getAllDepartment() {
        return masterService.getAllDepartment();
    }

    @GetMapping("/master/get-all-designation")
    public List<Designation> getAllDesignation() {
        return masterService.getAllDesignation();
    }

    @GetMapping("/master/get-all-account")
    public List<Account> getAllAccount() {
        return masterService.getAllAccount();
    }

    @GetMapping("/master/get-all-region")
    public List<Region> getAllRegion() {
        return masterService.getAllRegion();
    }

    @GetMapping("/master/get-all-location")
    public List<Location> getAllLocation() {
        return masterService.getAllLocation();
    }

    @GetMapping("/master/get-all-skill")
    public List<Skill> getAllSkill() {
        return masterService.getAllSkill();
    }

    @GetMapping("/master/get-all-service-line")
    public List<ServiceLine> getAllServiceLine() {
        return masterService.getAllServiceLine();
    }

    @GetMapping("/master/get-all-billable-status")
    public List<BillableStatus> getAllBillableStatus() {
        return masterService.getAllBillableStatus();
    }

    @GetMapping("/master/get-all-grade")
    public List<Grade> getAllGrade() {
        return masterService.getAllGrade();
    }

    @GetMapping("/master/get-all-academics")
    public List<Academics> getAllAcademics() {
        return masterService.getAllAcademics();
    }

    @GetMapping("/master/get-all-project-tagging")
    public List<ProjectTagging> getAllProjectTagging() {
        return masterService.getAllProjectTagging();
    }

//    @GetMapping("/master/get-all-project-tagging")
//    public List<Role> getAllRole() {
//        return masterService.getAllRole();
//    }
//
//    @GetMapping("/master/get-all-project-tagging")
//    public List<Project> getAllProject() {
//        return masterService.getAllProject();
//    }

}
