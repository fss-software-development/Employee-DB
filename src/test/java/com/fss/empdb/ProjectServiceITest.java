/*
package com.fss.empdb;

import com.fss.empdb.controller.ProjectController;
import com.fss.empdb.domain.*;
import com.fss.empdb.repository.ProjectRepository;
import com.fss.empdb.service.ProjectService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@DirtiesContext
//@Sql(scripts = {"/testdb/setup-empdb-db.sql"}, config = @SqlConfig(transactionMode = ISOLATED))
//@Sql(scripts = {"/testdb/tear-down-empdb-db.sql"}, config = @SqlConfig(transactionMode = ISOLATED), executionPhase = AFTER_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
public class ProjectServiceITest {


    @Autowired
    ProjectService projectService;

    @Before
    public void setup() {
    }

    private Project updateProject() {
        Project project = new Project();
        project.setProjectName("Employee-DB");
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Solution-OFSD");
        dept.setDepartmentHead("XYZkkk");
        //project.setDepartment(dept);
        project.setProjectName("Employee-DB");
        //project.setProjectManager("XYZkkk");
        //project.setProjectStatus("In-Progress");
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
        */
/*ProjectTagging projectTagging = new ProjectTagging();
        projectTagging.setProjectTaggingId(1L);
        projectTagging.setProjectTaggingName("Reserved");
        project.setProjectTagging(projectTagging);*//*

        return project;
    }

    @Test
    public void createProject_success(){
        Project project = newProject();
        System.out.println(project);
        Project newProject = projectService.createProject(project);
        assertNotNull(newProject);
        assertNotNull(newProject.getProjectId());
        assertEquals(project.getProjectName(), newProject.getProjectName());
        assertEquals(project.getAccount().getAccountId(), newProject.getAccount().getAccountId());
    }
    private ProjectSearchCriteria newProjectSearchCriteria(){
        ProjectSearchCriteria project = new ProjectSearchCriteria();
        project.setProjectId(2L);
        project.setProjectName("Employee-DB");
        Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Solution-OFSD");
        dept.setDepartmentHead("Vinod B");
        Department[] newDept= {dept};
        project.setDepartment(newDept);
        project.setProjectName("Employee-DB");
        project.setProjectManager("XYZ");
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
        //Project result = newProject.get(1);
        assertNotNull(newProject);
       */
/* assertNotNull(result.getProjectId());
        assertEquals(result.getProjectId(),project.getProjectId());
        assertEquals(result.getProjectName(), project.getProjectName());*//*

        //assertEquals(result.getDepartment(), project.getDepartment());
    }

    private Project newProject(){
        Project project = new Project();
        project.setProjectName("Employee-DB");
        */
/*Department dept = new Department();
        dept.setDepartmentId(1L);
        dept.setDepartmentName("Solution-OFSD");
        dept.setDepartmentHead("XYZkkk");
        project.setDepartment(dept);*//*

        project.setProjectName("Employee-DB");
        */
/*project.setProjectManager("XYZkkk");
        project.setProjectStatus("In-Progress");*//*

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
        */
/*ProjectTagging projectTagging = new ProjectTagging();
        projectTagging.setProjectTaggingId(1L);
        projectTagging.setProjectTaggingName("Reserved");
        project.setProjectTagging(projectTagging);*//*

        return project;
    }

    @Test
    public void updateProject_success() {
        Project project = updateProject();
        Project newProject = projectService.updateProject(project);
        assertNotNull(newProject);
        assertNotNull(newProject.getProjectId());

        assertNotNull(newProject.getProjectId());
        assertEquals(newProject.getProjectId(),project.getProjectId());
        assertEquals(newProject.getProjectName(), project.getProjectName());
       */
/* assertEquals(newProject.getDepartment().getDepartmentId(), project.getDepartment().getDepartmentId());*//*

    }

    @Test
    public void deleteProject_success() {
        Project project=new Project();
        project.setProjectId(54L);
        projectService.deleteProject(54L);

       // List<Project> result=projectService.allProject();
        //assertNotEquals(result.getProjectId(),project.getProjectId());
       // assertNull(result);

       */
/* ResponseEntity<Project> result=projectController.deleteProject(2L);
        System.out.println("result____"+result.getStatusCode());*//*

        */
/*Project project=new Project();
        project.setProjectId(54L);
        verify(projectRepository, times(1)).delete(project);*//*

    }


    */
/*@After
    public void tearDown() throws Exception{

    }*//*

    */
/*@AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }*//*

}
*/
