package com.fss.empdb.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.fss.empdb.exception.DuplicateRecordException;
import com.fss.empdb.util.ExceptionHandlerValidation;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fss.empdb.DTO.EmployeeCsvDto;
import com.fss.empdb.DTO.MasterDataDto;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.Academics;
import com.fss.empdb.domain.Account;
import com.fss.empdb.domain.BillableStatus;
import com.fss.empdb.domain.Department;
import com.fss.empdb.domain.Designation;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.EmployeeDefiniteRole;
import com.fss.empdb.domain.EmployeePossibleRole;
import com.fss.empdb.domain.EmployeeProject;
import com.fss.empdb.domain.EmployeeSearchCriteria;
import com.fss.empdb.domain.EmployeeSkill;
import com.fss.empdb.domain.EmployeeTools;
import com.fss.empdb.domain.Grade;
import com.fss.empdb.domain.Location;
import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.ProjectTagging;
import com.fss.empdb.domain.Region;
import com.fss.empdb.domain.Role;
import com.fss.empdb.domain.ServiceLine;
import com.fss.empdb.domain.Skill;
import com.fss.empdb.domain.Tools;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.EmployeeRepository;
import com.fss.empdb.util.FileUtil;
import com.fss.empdb.util.Util;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class EmployeeService {

    @Value("D:/Data/Project backup/CSV PATH")
    String UPLOADED_FOLDER;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MasterService masterService;


    public List<Employee> getAllEmployees() throws ParseException {
        List <Employee> employeeList= employeeRepository.findAll();
        for (Employee employee : employeeList)
        {
            computeTotalExp(employee);
        }
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) throws ParseException {
        Employee emp= employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
        Employee empNew = computeTotalExp(emp);
        return emp;
    }

    public Employee computeTotalExp(Employee emp) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date  date=simpleDateFormat.parse(emp.getJoiningDate().toString());
        LocalDate joiningDate=convertToLocalDateViaInstant(date);
        LocalDate currentDate = LocalDate.now();
        Period fssExp = Period.between(joiningDate, currentDate);
        long p2 = ChronoUnit.DAYS.between(joiningDate, currentDate);
        long  totalExp=fssExp.getYears()+emp.getPreviousExp();

        //log.info("You are " + p.getYears() + " years, " + p.getMonths() + " months, and " + p.getDays() + " days old. (" + p2 + " days total)");
        log.info("You are " + fssExp.getYears() + " years, ");

        emp.setTotalExperience(totalExp);
        return  emp;
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public List<Employee> findByEmp(EmployeeSearchCriteria emp) {
        log.info("Employee Search Service" + emp);
        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(emp.getEmployeeId().isEmpty())) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeId"), emp.getEmployeeId())));
                }
                if (emp.getEmployeeName() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("employeeName"), "%" + emp.getEmployeeName() + "%")));
                }
                if (emp.getDesignation().length > 0) {
                    Join<Employee, Designation> phoneJoin = root.join("designation");
                    predicates.add(phoneJoin.in(emp.getDesignation()));
                }
                if (emp.getDepartment().length > 0) {
                    Join<Employee, Department> phoneJoin = root.join("department");
                    predicates.add(phoneJoin.in(emp.getDepartment()));
                }
                if (emp.getRegion().length > 0) {
                    Join<Employee, Region> phoneJoin = root.join("region");
                    predicates.add(phoneJoin.in(emp.getRegion()));
                }
                if (emp.getAccount().length > 0l) {
                    log.info("Employee Search Account" + emp.getAccount());
                    Join<Employee, Account> phoneJoin = root.join("account");
                    predicates.add(phoneJoin.in(emp.getAccount()));
                }
                if (emp.getServiceLine().length > 0) {
                    Join<Employee, ServiceLine> phoneJoin = root.join("serviceLine");
                    predicates.add(phoneJoin.in(emp.getServiceLine()));
                }
                if (emp.getBillableStatus().length > 0) {
                    Join<Employee, BillableStatus> phoneJoin = root.join("billableStatus");
                    predicates.add(phoneJoin.in(emp.getBillableStatus()));
                }
                if (emp.getLocation().length > 0) {
                    Join<Employee, Location> phoneJoin = root.join("location");
                    predicates.add(phoneJoin.in(emp.getLocation()));
                }
                if (emp.getGrade().length > 0) {
                    Join<Employee, Grade> phoneJoin = root.join("grade");
                    predicates.add(phoneJoin.in(emp.getGrade()));
                }
                if (emp.getAcademics().length > 0) {
                    Join<Employee, Academics> phoneJoin = root.join("academics");
                    predicates.add(phoneJoin.in(emp.getAcademics()));
                }
                if (emp.getProjects().length > 0) {
                    Join<Employee, Project> phoneJoin = root.join("projects");
                    predicates.add(phoneJoin.in(emp.getProjects()));
                }
                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    public Employee createEmployee(Employee employee) {
        Employee empSave = null;
        try {
            log.info("SAVE " + employee.toString());
            employee.setInsUser(Long.valueOf(1));
            employee.setLastUpdateUser(Long.valueOf(1));
            employee.setInsDate(new Date());
            employee.setLastUpdateDate(new Date());
            empSave = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.employeeDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        }
        return empSave;
    }

    public Employee updateEmployee(Employee employee) {
        Employee empUpdate = null;
        try {
            Optional<Employee> emp = employeeRepository.findById(employee.getEmployeeSqId());
            Employee empEntity = emp.get();
            employee.setInsUser(empEntity.getInsUser());
            employee.setLastUpdateUser(Long.valueOf(1));
            employee.setInsDate(empEntity.getInsDate());
            employee.setLastUpdateDate(new Date());
            empUpdate = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException ex) {
            log.error("duplicate record", ex);
            String exceptionType = ExceptionHandlerValidation.employeeDuplicateHandler(ex);
            throw new DuplicateRecordException(exceptionType);
        }
        return empUpdate;
    }

    public List<String> getValuesForGivenKey(JSONArray jsonArrayStr, String key) {

        log.info("In function String:" + jsonArrayStr);
        log.info("In function key " + key);

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArrayStr.length())
                .mapToObj(index -> ((JSONObject) jsonArrayStr.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.EMPLOYEE_NOT_FOUND + employeeId));
        employeeRepository.delete(employee);
    }


    //save file
    public void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        log.info("File upload dir : " + UPLOADED_FOLDER);
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                log.info("File is empty!!");
                continue; //next pls
            }

            File savedFile = FileUtil.saveFile(UPLOADED_FOLDER,file);
            processFile(savedFile);
        }

    }

    private void processFile(File file) throws IOException {

        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

        CsvMapper mapper = new CsvMapper();

        MappingIterator<Map<String, Object>> readValues1 = mapper
                .readerFor(LinkedHashMap.class)
                .with(bootstrapSchema)
                .readValues(file);

        List<EmployeeCsvDto> employeeRecords = readValues1.readAll().stream().map(EmployeeCsvDto::new)
                .collect(Collectors.toList());

        log.info(employeeRecords.toString());

        processEmployeeCsvRecords(employeeRecords);

    }

    private void processEmployeeCsvRecords(List<EmployeeCsvDto> employeeRecords) {

        // get all master data from tables
        MasterDataDto masterDataDto = getAllMasterDatas();

        if (!CollectionUtils.isEmpty(employeeRecords)) {
            for (EmployeeCsvDto employeeCsvDto : employeeRecords) {
                try {
                    Employee employee = createEmployeeEntity(employeeCsvDto, masterDataDto);
                    saveEmployeeData(employee);
                } catch (Exception e) {
                    log.error("Exception occured while process Employee CSV Record with ID: {}, {}", employeeCsvDto.getEmployeeId(), e);
                }
            }
        }

    }

    @Transactional
    private void saveEmployeeData(final Employee employee) {
        employeeRepository.save(employee);
    }

    private Employee createEmployeeEntity(final EmployeeCsvDto employeeCsvDto, final MasterDataDto masterDataDto) throws ParseException {

        final Department department = getDepartmentDtlsByName(masterDataDto.getDepartments(), employeeCsvDto.getDepartmentName());
        final Account account = getAccountDtlsByName(masterDataDto.getAccounts(), employeeCsvDto.getAccount());
        final Region region = getRegionDtlsByName(masterDataDto.getRegions(), employeeCsvDto.getRegion());
        final Location location = getLocationDtlsByName(masterDataDto.getLocations(), employeeCsvDto.getWorkingLocation());
        final Grade grade = getGradeDtlsByName(masterDataDto.getGrades(), employeeCsvDto.getGrade());
        final Designation designation = getDesignationDtlsByName(masterDataDto.getDesignations(), employeeCsvDto.getDesignation());
        final BillableStatus billableStatus = getBillableStatusDtlsByName(masterDataDto.getBillableStatus(), employeeCsvDto.getBillableStatus());
        final ServiceLine serviceLine = getServiceLineDtlsByName(masterDataDto.getServiceLines(), employeeCsvDto.getServiceLine());
        final Academics academics = getAcademicsDtlsByName(masterDataDto.getAcademics(), employeeCsvDto.getEducationalQualification());
        final ProjectTagging projectTagging = getProjectTaggingDtlsByName(masterDataDto.getProjectTaggings(), employeeCsvDto.getProjectTagging());

        Employee employee = Employee.builder()
                .employeeId(employeeCsvDto.getEmployeeId())
                .employeeName(employeeCsvDto.getEmployeeName())
                .mobileNum(Long.valueOf(employeeCsvDto.getMobileNumber()))
                .emailId(employeeCsvDto.getEmailID())
                .reportingManager(employeeCsvDto.getReportingHead())
                .activityName(employeeCsvDto.getActivityName())
                .joiningDate(Util.convertStringToDate(employeeCsvDto.getDoj()))
                .previousExp(Long.valueOf(employeeCsvDto.getPreviousExperience()))
                .experienceGaps(Long.valueOf(employeeCsvDto.getExperienceGaps()))
                .experienceCurrentRole(Long.valueOf(employeeCsvDto.getExperienceCurrentRole()))
                .totalExperience(Long.valueOf(employeeCsvDto.getTotalExperience()))
                .department(department)
                .account(account)
                .region(region)
                .location(location)
                .grade(grade)
                .designation(designation)
                .billableStatus(billableStatus)
                .serviceLine(serviceLine)
                .academics(academics)
                .projectTagging(projectTagging)
                .insUser(1L)
                .insDate(new Date())
                .lastUpdateUser(1L)
                .lastUpdateDate(new Date())
                .build();

//        setEmployeeProjects(masterDataDto.getProjects(), employeeCsvDto, employee);

//        setEmployeeSkills(masterDataDto.getSkills(), employeeCsvDto, employee);

//        setEmployeeTools(masterDataDto.getTools(), employeeCsvDto, employee);
//
//        setEmployeeDefiniteRoles(masterDataDto.getRoles(), employeeCsvDto, employee);

//        setEmployeePossibleRoles(masterDataDto.getRoles(), employeeCsvDto, employee);

        return employee;
    }

//    private void setEmployeePossibleRoles(final Map<String, Role> rolesMap, final EmployeeCsvDto employeeCsvDto, Employee employee) {
//        Set<EmployeePossibleRole> employeePossibleRoles = new HashSet<>();
//
//        String[] possibleRoles = StringUtils.split(employeeCsvDto.getPossibleRole(), ';');
//        for (String roleName : possibleRoles) {
//            if (rolesMap.containsKey(roleName)) {
//                employeePossibleRoles.add(EmployeePossibleRole.builder()
//                        .employee(employee)
//                        .role(rolesMap.get(roleName))
//                        .build());
//            } else {
//                throw new ResourceNotFoundException("Role Name : " + roleName + " not found in Master Data");
//            }
//        }
//
//        if (!CollectionUtils.isEmpty(employeePossibleRoles)) {
//            employee.setEmployeePossibleRoles(employeePossibleRoles);
//        }
//    }

//    private void setEmployeeDefiniteRoles(final Map<String, Role> rolesMap, final EmployeeCsvDto employeeCsvDto, Employee employee) {
//        Set<EmployeeDefiniteRole> employeeDefiniteRoles = new HashSet<>();
//
//        String[] definiteRoles = StringUtils.split(employeeCsvDto.getDefineRole(), ';');
//        for (String roleName : definiteRoles) {
//            if (rolesMap.containsKey(roleName)) {
//                employeeDefiniteRoles.add(EmployeeDefiniteRole.builder()
//                        .employee(employee)
//                        .role(rolesMap.get(roleName))
//                        .build());
//            } else {
//                throw new ResourceNotFoundException("Role Name : " + roleName + " not found in Master Data");
//            }
//        }
//
//        if (!CollectionUtils.isEmpty(employeeDefiniteRoles)) {
//            employee.setEmployeeDefiniteRoles(employeeDefiniteRoles);
//        }
//
//    }

//    private void setEmployeeTools(final Map<String, Tools> toolsMap, final EmployeeCsvDto employeeCsvDto, Employee employee) {
//        Set<EmployeeTools> employeeTools = new HashSet<>();
//
//        String[] tools = StringUtils.split(employeeCsvDto.getTools(), ';');
//        for (String toolName : tools) {
//            if (toolsMap.containsKey(toolName)) {
//                employeeTools.add(EmployeeTools.builder()
//                        .employee(employee)
//                        .tools(toolsMap.get(toolName))
//                        .build());
//            } else {
//                throw new ResourceNotFoundException("Tool Name : " + toolName + " not found in Master Data");
//            }
//        }
//
//        if (!CollectionUtils.isEmpty(employeeTools)) {
//            employee.setEmployeeTools(employeeTools);
//        }
//    }

//    private void setEmployeeSkills(final Map<String, Skill> skillsMap, final EmployeeCsvDto employeeCsvDto, Employee employeeEntity) {
//        Set<EmployeeSkill> employeeSkills = new HashSet<>();
//
//        Map<String, String> entries = employeeCsvDto.getSkills();
//
//        if (!CollectionUtils.isEmpty(entries)) {
//            for (Map.Entry<String, String> entry : entries.entrySet()) {
//
//                final String skillName = entry.getKey();
//                final String value = entry.getValue();
//
//                if (skillsMap.containsKey(skillName)) {
//                    if (!StringUtils.isEmpty(value)) {
//                        EmployeeSkill employeeSkill = new EmployeeSkill();
//                        employeeSkill.setEmployee(employeeEntity);
//                        employeeSkill.setSkill(skillsMap.get(skillName));
//                        employeeSkill.setInsUser(1L);
//                        if ("Certified".equalsIgnoreCase(value)) {
//                            employeeSkill.setCertified(1L);
//                            employeeSkill.setUsedInProject(1L);
//                        } else if ("Not Certified".equalsIgnoreCase(value)) {
//                            employeeSkill.setCertified(0L);
//                        }
//
//                        employeeSkills.add(employeeSkill);
//                    }
//
//                } else {
//                    throw new ResourceNotFoundException("Skill Name : " + skillName + " not found in Master Data");
//                }
//            }
//
//            if (!CollectionUtils.isEmpty(employeeSkills)) {
//                employeeEntity.setEmployeeSkills(employeeSkills);
//            }
//        }
//    }

//    private void setEmployeeProjects(final Map<String, Project> projectsMap, final EmployeeCsvDto employeeCsvDto, Employee employeeEntity) throws ParseException{
//        Set<EmployeeProject> employeeProjects = new HashSet<>();
//
//        Map<String, String> entries = employeeCsvDto.getProjects();
//
//        if (!CollectionUtils.isEmpty(entries)) {
//            for (Map.Entry<String, String> entry : entries.entrySet()) {
//
//                final String projectName = entry.getKey();
//                final String duration = entry.getValue();
//
//                if (projectsMap.containsKey(projectName)) {
//                    if (!StringUtils.isEmpty(duration)) {
//                        EmployeeProject employeeProject = new EmployeeProject();
//                        employeeProject.setEmployee(employeeEntity);
//                        employeeProject.setProject(projectsMap.get(projectName));
//                        employeeProject.setInsUser(1L);
//
//                        // spilt project durations
//                        String[] durations = StringUtils.split(duration, '/');
//                        employeeProject.setStartDate(Util.convertStringToDate(durations[0]));
//                        employeeProject.setEndDate(Util.convertStringToDate(durations[1]));
//
//                        employeeProjects.add(employeeProject);
//                    }
//
//                } else {
//                    throw new ResourceNotFoundException("Project Name : " + projectName + " not found in Master Data");
//                }
//            }
//
//            if (!CollectionUtils.isEmpty(employeeProjects)) {
//                employeeEntity.setEmployeeProjects(employeeProjects);
//            }
//        }
//    }

    private ProjectTagging getProjectTaggingDtlsByName(final Map<String, ProjectTagging> projectTaggingMap, final String projectTaggingName) {
        ProjectTagging projectTagging = null;

        if (projectTaggingMap.containsKey(projectTaggingName)) {
            projectTagging = projectTaggingMap.get(projectTaggingName);
        } else {
            throw new ResourceNotFoundException("Project Tagging Name : " + projectTaggingName + " not found in Master Data");
        }

        return projectTagging;
    }

    private Academics getAcademicsDtlsByName(final Map<String, Academics> academicsMap, final String academicName) {
        Academics academics = null;

        if (academicsMap.containsKey(academicName)) {
            academics = academicsMap.get(academicName);
        } else {
            throw new ResourceNotFoundException("Academic Name : " + academicName + " not found in Master Data");
        }

        return academics;
    }

    private ServiceLine getServiceLineDtlsByName(final Map<String, ServiceLine> serviceLineMap, final String serviceLineName) {
        ServiceLine serviceLine = null;

        if (serviceLineMap.containsKey(serviceLineName)) {
            serviceLine = serviceLineMap.get(serviceLineName);
        } else {
            throw new ResourceNotFoundException("Service Line Name : " + serviceLineName + " not found in Master Data");
        }

        return serviceLine;
    }

    private BillableStatus getBillableStatusDtlsByName(final Map<String, BillableStatus> billableStatusMap, final String billableStatusName) {
        BillableStatus billableStatus = null;

        if (billableStatusMap.containsKey(billableStatusName)) {
            billableStatus = billableStatusMap.get(billableStatusName);
        } else {
            throw new ResourceNotFoundException("Billable Status Name : " + billableStatusName + " not found in Master Data");
        }

        return billableStatus;
    }

    private Designation getDesignationDtlsByName(final Map<String, Designation> designationMap, final String designationName) {
        Designation designation = null;

        if (designationMap.containsKey(designationName)) {
            designation = designationMap.get(designationName);
        } else {
            throw new ResourceNotFoundException("Designation Name : " + designationName + " not found in Master Data");
        }

        return designation;
    }

    private Grade getGradeDtlsByName(final Map<String, Grade> gradeMap, final String gradeName) {
        Grade grade = null;

        if (gradeMap.containsKey(gradeName)) {
            grade = gradeMap.get(gradeName);
        } else {
            throw new ResourceNotFoundException("Grade Name : " + gradeName + " not found in Master Data");
        }

        return grade;
    }

    private Location getLocationDtlsByName(final Map<String, Location> locationMap, final String locationName) {
        Location region = null;

        if (locationMap.containsKey(locationName)) {
            region = locationMap.get(locationName);
        } else {
            throw new ResourceNotFoundException("Location Name : " + locationName + " not found in Master Data");
        }

        return region;
    }

    private Region getRegionDtlsByName(final Map<String, Region> regionMap, final String regionName) {
        Region region = null;

        if (regionMap.containsKey(regionName)) {
            region = regionMap.get(regionName);
        } else {
            throw new ResourceNotFoundException("Region Name : " + regionName + " not found in Master Data");
        }

        return region;
    }

    private Account getAccountDtlsByName(final Map<String, Account> accountMap, final String accountName) {
        Account account = null;

        if (accountMap.containsKey(accountName)) {
            account = accountMap.get(accountName);
        } else {
            throw new ResourceNotFoundException("Account Name : " + accountName + " not found in Master Data");
        }

        return account;
    }

    private Department getDepartmentDtlsByName(final Map<String, Department> departmentMap, final String departmentName) {
        Department department = null;

        if (departmentMap.containsKey(departmentName)) {
            department = departmentMap.get(departmentName);
        } else {
            throw new ResourceNotFoundException("Department Name : " + departmentName + " not found in Master Data");
        }

        return department;
    }

    private MasterDataDto getAllMasterDatas() {
        return MasterDataDto.builder()
                .academics(constructMasterAcademicsMap())
                .accounts(constructMasterAccountMap())
                .billableStatus(constructMasterBillableStatusMap())
                .departments(constructMasterDepartmentMap())
                .designations(constructMasterDesignationMap())
                .grades(constructMasterGradeMap())
                .locations(constructMasterLocationMap())
                .projects(constructMasterProjectMap())
                .projectTaggings(constructMasterProjectTaggingMap())
                .regions(constructMasterRegionMap())
                .roles(constructMasterRolesMap())
                .serviceLines(constructMasterServiceLineMap())
                .skills(constructMasterSkillsMap())
                .tools(constructMasterToolsMap())
                .build();
    }

    private Map<String, Department> constructMasterDepartmentMap() {
        Map<String, Department> departmentMap = new HashMap<>();

        List<Department> departments = masterService.getAllDepartment();

        if (!CollectionUtils.isEmpty(departments)) {
            departmentMap = departments.stream()
                    .collect(Collectors.toMap(Department::getDepartmentName, department -> department));
        }

        return departmentMap;
    }

    private Map<String, Account> constructMasterAccountMap(){
        Map<String, Account> accountMap = new HashMap<>();

        List<Account> accounts = masterService.getAllAccount();

        if (!CollectionUtils.isEmpty(accounts)) {
            accountMap = accounts.stream()
                    .collect(Collectors.toMap(Account::getAccountName, account -> account));
        }

        return accountMap;
    }

    private Map<String, Region> constructMasterRegionMap(){
        Map<String, Region> regionMap = new HashMap<>();

        List<Region> regions = masterService.getAllRegion();

        if (!CollectionUtils.isEmpty(regions)) {
            regionMap = regions.stream()
                    .collect(Collectors.toMap(Region::getRegionName, region -> region));
        }

        return regionMap;
    }

    private Map<String, Location> constructMasterLocationMap(){
        Map<String, Location> locationMap = new HashMap<>();

        List<Location> locations = masterService.getAllLocation();

        if (!CollectionUtils.isEmpty(locations)) {
            locationMap = locations.stream()
                    .collect(Collectors.toMap(Location::getLocationName, location -> location));
        }

        return locationMap;
    }

    private Map<String, Grade> constructMasterGradeMap(){
        Map<String, Grade> gradeMap = new HashMap<>();

        List<Grade> grades = masterService.getAllGrade();

        if (!CollectionUtils.isEmpty(grades)) {
            gradeMap = grades.stream()
                    .collect(Collectors.toMap(Grade::getGradeName, grade -> grade));
        }

        return gradeMap;
    }

    private Map<String, Designation> constructMasterDesignationMap(){
        Map<String, Designation> designationMap = new HashMap<>();

        List<Designation> designations = masterService.getAllDesignation();

        if (!CollectionUtils.isEmpty(designations)) {
            designationMap = designations.stream()
                    .collect(Collectors.toMap(Designation::getDesignationName, designation -> designation));
        }

        return designationMap;
    }

    private Map<String, BillableStatus> constructMasterBillableStatusMap(){
        Map<String, BillableStatus> billableStatusMap = new HashMap<>();

        List<BillableStatus> listOfbillableStatus = masterService.getAllBillableStatus();

        if (!CollectionUtils.isEmpty(listOfbillableStatus)) {
            billableStatusMap = listOfbillableStatus.stream()
                    .collect(Collectors.toMap(BillableStatus::getBillableStatus, billableStatus -> billableStatus));
        }

        return billableStatusMap;
    }

    private Map<String, ServiceLine> constructMasterServiceLineMap(){
        Map<String, ServiceLine> serviceLineMap = new HashMap<>();

        List<ServiceLine> listOfServiceLine = masterService.getAllServiceLine();

        if (!CollectionUtils.isEmpty(listOfServiceLine)) {
            serviceLineMap = listOfServiceLine.stream()
                    .collect(Collectors.toMap(ServiceLine::getServiceLineName, serviceLine -> serviceLine));
        }

        return serviceLineMap;
    }

    private Map<String, ProjectTagging> constructMasterProjectTaggingMap(){
        Map<String, ProjectTagging> projectTaggingMap = new HashMap<>();

        List<ProjectTagging> listOfProjectTagging = masterService.getAllProjectTagging();

        if (!CollectionUtils.isEmpty(listOfProjectTagging)) {
            projectTaggingMap = listOfProjectTagging.stream()
                    .collect(Collectors.toMap(ProjectTagging::getProjectTaggingName, projectTagging -> projectTagging));
        }

        return projectTaggingMap;
    }

    private Map<String, Academics> constructMasterAcademicsMap(){
        Map<String, Academics> academicsMap = new HashMap<>();

        List<Academics> listOfAcademics = masterService.getAllAcademics();

        if (!CollectionUtils.isEmpty(listOfAcademics)) {
            academicsMap = listOfAcademics.stream()
                    .collect(Collectors.toMap(Academics::getAcademicsName, academics -> academics));
        }

        return academicsMap;
    }

    private Map<String, Project> constructMasterProjectMap(){
        Map<String, Project> projectMap = new HashMap<>();

        List<Project> listOfProjects = masterService.getAllProject();

        if (!CollectionUtils.isEmpty(listOfProjects)) {
            projectMap = listOfProjects.stream()
                    .collect(Collectors.toMap(Project::getProjectName, project -> project));
        }

        return projectMap;
    }

    private Map<String, Skill> constructMasterSkillsMap(){
        Map<String, Skill> skillMap = new HashMap<>();

        List<Skill> listOfskills = masterService.getAllSkill();

        if (!CollectionUtils.isEmpty(listOfskills)) {
            skillMap = listOfskills.stream()
                    .collect(Collectors.toMap(Skill::getSkillName, skill -> skill));
        }

        return skillMap;
    }

    private Map<String, Tools> constructMasterToolsMap(){
        Map<String, Tools> toolsMap = new HashMap<>();

        List<Tools> listOfTools = masterService.getAllTools();

        if (!CollectionUtils.isEmpty(listOfTools)) {
            toolsMap = listOfTools.stream()
                    .collect(Collectors.toMap(Tools::getToolName, tool -> tool));
        }

        return toolsMap;
    }

    private Map<String, Role> constructMasterRolesMap(){
        Map<String, Role> rolesMap = new HashMap<>();

        List<Role> listOfRoles = masterService.getAllRole();

        if (!CollectionUtils.isEmpty(listOfRoles)) {
            rolesMap = listOfRoles.stream()
                    .collect(Collectors.toMap(Role::getRoleName, role -> role));
        }

        return rolesMap;
    }

}

