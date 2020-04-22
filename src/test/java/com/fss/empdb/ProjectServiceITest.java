package com.fss.empdb;

import com.fss.empdb.domain.*;
import com.fss.empdb.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@DirtiesContext
//@Sql(scripts = {"/db/setup-empdb-db.sql"}, config = @SqlConfig(transactionMode = ISOLATED))
//@Sql(scripts = {"/db/tear-down-empdb-db.sql"}, config = @SqlConfig(transactionMode = ISOLATED), executionPhase = AFTER_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
@ActiveProfiles("local")

public class ProjectServiceITest {


    @Autowired
    ProjectService projectService;

    private Project updateProject() {
        Project project = new Project();
        project.setProjectName("Employee-DB");
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Solution-OFSD");
        dept.setDepartmentHead("XYZkkk");
        project.setDepartment(dept);
        project.setProjectName("Employee-DB");
        project.setProjectManager("XYZkkk");
        project.setProjectStatus("In-Progress");
        project.setProjectStartDate(new Date(2020-04-02));
        project.setProjectEndDate(new Date(2021-04-02));
        Region reg = new Region();
        reg.setRegionId(1L);
        reg.setRegionName("US");
        project.setRegion(reg);
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountName("HDFC");
        project.setAccount(account);
        ProjectTagging projectTagging = new ProjectTagging();
        projectTagging.setProjectTaggingId(1L);
        projectTagging.setProjectTaggingName("Reserved");
        project.setProjectTagging(projectTagging);
        return project;
    }

    @Test
    public void createProject_success(){
        Project project = newProject();
        System.out.println(projectService);
        Project newProject = projectService.createProject(project);

        assertNotNull(newProject);
        assertNotNull(newProject.getProjectId());
    }
    private ProjectSearchCriteria newProjectSearchCriteria(){
        ProjectSearchCriteria project = new ProjectSearchCriteria();
        project.setProjectName("Employee-DB");
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Solution-OFSD");
        dept.setDepartmentHead("XYZkkk");
        Department[] newDept= {dept};
        project.setDepartment(newDept);
        project.setProjectName("Employee-DB");
        project.setProjectManager("XYZkkk");
        project.setProjectStatus("In-Progress");
        project.setProjectStartDate(new Date(2020-04-02));
        project.setProjectEndDate(new Date(2021-04-02));
        Region reg = new Region();
        reg.setRegionId(1L);
        reg.setRegionName("US");
        Region[] newReg= {reg};
        project.setRegion(newReg);
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountName("HDFC");
        Account[] newAccount= {account};
        project.setAccount(newAccount);
        ProjectTagging projectTagging = new ProjectTagging();
        projectTagging.setProjectTaggingId(1L);
        projectTagging.setProjectTaggingName("Reserved");
        ProjectTagging[] newProjectTagging= {projectTagging};
        project.setProjectTagging(newProjectTagging);
        return project;
    }

    @Test
    public void projectsBySearch_success(){
        ProjectSearchCriteria project = newProjectSearchCriteria();
        System.out.println(projectService);
        List<Project> newProject = projectService.projectsBySearch(project);

        assertNotNull(newProject);
        //assertNotNull(newProject.getProjectId());
    }

    private Project newProject(){
        Project project = new Project();
        project.setProjectName("Employee-DB");
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Solution-OFSD");
        dept.setDepartmentHead("XYZkkk");
        project.setDepartment(dept);
        project.setProjectName("Employee-DB");
        project.setProjectManager("XYZkkk");
        project.setProjectStatus("In-Progress");
        project.setProjectStartDate(new Date(2020-04-02));
        project.setProjectEndDate(new Date(2021-04-02));
        Region reg = new Region();
        reg.setRegionId(1L);
        reg.setRegionName("US");
        project.setRegion(reg);
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountName("HDFC");
        project.setAccount(account);
        ProjectTagging projectTagging = new ProjectTagging();
        projectTagging.setProjectTaggingId(1L);
        projectTagging.setProjectTaggingName("Reserved");
        project.setProjectTagging(projectTagging);
        return project;
    }

    @Test
    public void updateProject_success() {
        Project project = updateProject();
        Project newProject = projectService.updateProject(project);
        assertNotNull(newProject);
        assertNotNull(newProject.getProjectId());
    }

    @Test
    public void deleteAccount_success() {
        projectService.deleteProject(27L);
    }


    /*@After
    public void tearDown() throws Exception{

    }*/
    /*@AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }*/
}
