package com.fss.empdb.service;

import com.fss.empdb.domain.*;
import com.fss.empdb.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
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

    @Autowired
    ToolsRepository toolsRepository;

    @Autowired
    ServiceTypeRepository serviceTypeREpository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StatusRepository statusRepository;

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

    public List<Tools> getAllTools() {
        return toolsRepository.findAll();
    }

    public List<ServiceType> getAllServiceType() {
        return serviceTypeREpository.findAll();
    }

    public List<ProductType> getAllProductType() {
        return productTypeRepository.findAll();
    }

    public List<Product> getAllProduct(Long productTypeId) {
        return productRepository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (productTypeId != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("productTypeId"), productTypeId)));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

}
