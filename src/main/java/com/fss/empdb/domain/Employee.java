package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Employee {



    @Id
    @Column(name="EMP_ID")
    Long empId;

    public Employee(){
        /*
        default constructor
         */
    }

    @Column(name="EMP_CODE",nullable = false)
    Long empCode;

    @Column(name="EMP_NAME",nullable = false)
    String empName;

    @Column(name="DESIGNATION_ID",nullable = false)
    Long designationId;

    @Column(name="DEPARTMENT_ID",nullable = false)
    Long departmentId;

    @Column(name="REGION_ID",nullable = false)
    Long regionId;

    @Column(name="ACCOUNT_ID",nullable = false)
    Long accountId;

    @Column(name="SERVICE_LINE_ID",nullable = false)
    Long serviceLineId;

    @Column(name="BILLABLE_STATUS_ID",nullable = false)
    Long billableStatusId;

    @Column(name="PROJECT_ID",nullable = false)
    Long projectId;

    @Column(name="ACTIVITY_NAME",nullable = false)
    String activityName;

    @Column(name="REPORTING_HEAD",nullable = false)
    String reportingHead;

    @Column(name="LOCATION_ID",nullable = false)
    Long locationId;

    @Column(name="GRADE_ID",nullable = false)
    String gradeId;

    @Column(name="ACADEMICS_ID",nullable = false)
    String academicsId;

    @Column(name="DEFINITE_ROLE")
    String definiteRole;

    @Column(name="POSSIBLE_ROLE",nullable = false)
    String possibleRole;

    @Column(name="PREVIOUS_EXPERIENCE",nullable = false)
    String previousExp;

    @Temporal(TemporalType.DATE)
    @Column(name="FSS_JOINING_DATE",nullable = false)
    Date fssJoiningDate;

    @Column(name="EXPERIENCE_CURRENT_ROLE",nullable = false)
    String  experienceCureentRole;

    @Column(name="SKILL",nullable = false)
    String skill;

    @Column(name="TOOLS",nullable = false)
    String tools;

    @Column(name="PROJECT_TAGGING",nullable = false)
    String projectTagging;

    @Temporal(TemporalType.DATE)
    @Column(name="PROJECT_START_DATE",nullable = false)
    Date projectStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name="PROJECT_END_DATE",nullable = false)
    Date projectEndDate;

    @Column(name="PROFILE",nullable = false)
    String profile;


}
