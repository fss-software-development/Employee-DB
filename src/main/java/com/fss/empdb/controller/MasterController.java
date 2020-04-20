package com.fss.empdb.controller;

import com.fss.empdb.domain.*;
import com.fss.empdb.service.MasterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/master")
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping("/department")
    public List<Department> getAllDepartment() {
        return masterService.getAllDepartment();
    }

    @GetMapping("/designation")
    public List<Designation> getAllDesignation() {
        return masterService.getAllDesignation();
    }

    @GetMapping("/account")
    public List<Account> getAllAccount() {
        return masterService.getAllAccount();
    }

    @GetMapping("/region")
    public List<Region> getAllRegion() {
        return masterService.getAllRegion();
    }

    @GetMapping("/location")
    public List<Location> getAllLocation() {
        return masterService.getAllLocation();
    }

    @GetMapping("/skill")
    public List<Skill> getAllSkill() {
        return masterService.getAllSkill();
    }

    @GetMapping("/service-line")
    public List<ServiceLine> getAllServiceLine() {
        return masterService.getAllServiceLine();
    }

    @GetMapping("/billable-status")
    public List<BillableStatus> getAllBillableStatus() {
        return masterService.getAllBillableStatus();
    }

    @GetMapping("/grade")
    public List<Grade> getAllGrade() {
        return masterService.getAllGrade();
    }

    @GetMapping("/academics")
    public List<Academics> getAllAcademics() {
        return masterService.getAllAcademics();
    }

    @GetMapping("/project-tagging")
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
