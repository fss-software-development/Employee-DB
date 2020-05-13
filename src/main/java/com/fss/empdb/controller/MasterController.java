package com.fss.empdb.controller;

import com.fss.empdb.domain.*;
import com.fss.empdb.service.MasterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/masters")
public class MasterController {

    @Autowired
    MasterService masterService;

    @GetMapping("/departments")
    public List<Department> getAllDepartment() {
        return masterService.getAllDepartment();
    }

    @GetMapping("/designations")
    public List<Designation> getAllDesignation() {
        return masterService.getAllDesignation();
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccount() {
        return masterService.getAllAccount();
    }

    @GetMapping("/regions")
    public List<Region> getAllRegion() {
        return masterService.getAllRegion();
    }

    @GetMapping("/locations")
    public List<Location> getAllLocation() {
        return masterService.getAllLocation();
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkill() {
        return masterService.getAllSkill();
    }

    @GetMapping("/serviceLine")
    public List<ServiceLine> getAllServiceLine() {
        return masterService.getAllServiceLine();
    }

    @GetMapping("/billableStatus")
    public List<BillableStatus> getAllBillableStatus() {
        return masterService.getAllBillableStatus();
    }

    @GetMapping("/grades")
    public List<Grade> getAllGrade() {
        return masterService.getAllGrade();
    }

    @GetMapping("/academics")
    public List<Academics> getAllAcademics() {
        return masterService.getAllAcademics();
    }

    @GetMapping("/projectTagging")
    public List<ProjectTagging> getAllProjectTagging() {
        return masterService.getAllProjectTagging();
    }

    @GetMapping("/tools")
    public List<Tools> getAllTools() {
        return masterService.getAllTools();
    }

    @GetMapping("/roles")
    public List<Role> getAllRole() {
        return masterService.getAllRole();
    }

    @GetMapping("/serviceType")
    public List<ServiceType> getAllServiceType() {
        return masterService.getAllServiceType();
    }

    @GetMapping("/productType")
    public List<ProductType> getAllProductType() {
        return masterService.getAllProductType();
    }

    @GetMapping("/product/{id}")
    public List<Product> getAllProduct(@PathVariable(value = "id") Long productTypeId) {
        log.info("Controller --------------------" + productTypeId);
        return masterService.getAllProduct(productTypeId);
    }

    @GetMapping("/projectStatus")
    public List<Status> getAllStatus() {
        return masterService.getAllStatus();
    }
}
