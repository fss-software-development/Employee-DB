package com.fss.empdb.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_SQID")
    Long employeeSqId;

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
    private Collection<Department> department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Account> account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<Region> region;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
    private Collection<Location> location;

    @Column(name = "FIRST_NAME", nullable = false)
    String firstName;

    @Column(name = "MIDDLE_NAME", nullable = false)
    String middleName;

    @Column(name = "LAST_NAME", nullable = false)
    String lastName;

    @Column(name = "MOBILE_NUM", nullable = false)
    Long mobileNum;

    @Column(name = "EMAIL_ID", nullable = false)
    String emailId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeId")
    private Collection<Grade> grade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "designationId")
    private Collection<Designation> designation;

    @Column(name = "REPORTING_MANAGER", nullable = false)
    String reportingManager;

    @Column(name = "PREVIOUS_EXP", nullable = false)
    String previousExp;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINING_DATE", nullable = false)
    Date joiningDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billableStatusId")
    private Collection<BillableStatus> billableStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceLineId")
    private Collection<ServiceLine> serviceLine;

    @Column(name = "ACTIVITY_NAME", nullable = false)
    String activityName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillId")
    private Collection<Skill> primarySkill;

    @Column(name = "EXPERIENCE_GAPS", nullable = false)
    Long experienceGaps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<Role> role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicsId")
    private Collection<Academics> academics;

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectTaggingId")
    private Collection<ProjectTagging> ProjectTagging;*/

    @Column(name = "INS_USER", nullable = false)
    Long insUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "INS_DATE", nullable = false)
    Date insDate;

    @Column(name = "LAST_UPDATE_USER", nullable = false)
    Long lastUpdateUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    Date lastUpdateDate;

    @JsonIgnore
    @Column(name="DESIGNATION_ID",nullable = false,insertable = false, updatable = false)
    Long designationId;

    @JsonIgnore
    @Column(name = "DEPARTMENT_ID", nullable = false,insertable = false, updatable = false)
    private Long departmentId;

    @JsonIgnore
    @Column(name = "REGION_Id", nullable = false,insertable = false, updatable = false)
    private Long regionId;

    @JsonIgnore
    @Column(name="ACCOUNT_ID",nullable = false,insertable = false, updatable = false)
    Long accountId;

    @JsonIgnore
    @Column(name="SERVICE_LINE_ID",nullable = false,insertable = false, updatable = false)
    Long serviceLineId;

    @JsonIgnore
    @Column(name="BILLABLE_STATUS_ID",nullable = false,insertable = false, updatable = false)
    Long billableStatusId;

    /*@Column(name="PROJECT_ID",nullable = false,insertable = false, updatable = false)
    Long projectId;*/

    @JsonIgnore
    @Column(name="LOCATION_ID",nullable = false,insertable = false, updatable = false)
    Long locationId;

    @JsonIgnore
    @Column(name="GRADE_ID",nullable = false,insertable = false, updatable = false)
    Long gradeId;

    @JsonIgnore
    @Column(name="ACADEMICS_ID",nullable = false,insertable = false, updatable = false)
    Long academicId;

   /* @Column(name="PROJECT_TAGGING_ID",nullable = false,insertable = false, updatable = false)
    Long projectTaggingId;*/

}
