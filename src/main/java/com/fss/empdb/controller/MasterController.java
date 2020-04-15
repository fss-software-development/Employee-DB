package com.fss.empdb.controller;

import com.fss.empdb.domain.*;
import com.fss.empdb.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping("/get-all-department")
    public List<Department> getAllDepartment() {
        return masterService.getAllDepartment();
    }

    @GetMapping("/get-all-designation")
    public List<Designation> getAllDesignation() {
        return masterService.getAllDesignation();
    }

    @GetMapping("/get-all-account")
    public List<Account> getAllAccount() {
        return masterService.getAllAccount();
    }

    @GetMapping("/get-all-region")
    public List<Region> getAllRegion() {
        return masterService.getAllRegion();
    }

    @GetMapping("/get-all-location")
    public List<Location> getAllLocation() {
        return masterService.getAllLocation();
    }

    @GetMapping("/get-all-skill")
    public List<Skill> getAllSkill() {
        return masterService.getAllSkill();
    }

    @GetMapping("/get-all-service-line")
    public List<ServiceLine> getAllServiceLine() {
        return masterService.getAllServiceLine();
    }

    @GetMapping("/get-all-billable-status")
    public List<BillableStatus> getAllBillableStatus() {
        return masterService.getAllBillableStatus();
    }

    @GetMapping("/get-all-grade")
    public List<Grade> getAllGrade() {
        return masterService.getAllGrade();
    }

    @GetMapping("/get-all-academics")
    public List<Academics> getAllAcademics() {
        return masterService.getAllAcademics();
    }

    @GetMapping("/get-all-project-tagging")
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
