package com.fss.empdb.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity

public class Employee {

    @Id
    @Column(name = "EMPLOYEE_SQID")
    Long employeeSqId;

    public Employee() {
    }

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

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

    @OneToOne
    @JoinColumn(name="GRADE_ID")
    private Grade grade;

    @OneToOne
    @JoinColumn(name="DESIGNATION_ID")
    private Designation designation;

    @Column(name = "REPORTING_MANAGER", nullable = false)
    String reportingManager;

    @OneToOne
    @JoinColumn(name="DEPARTMENT_ID")
    private Department department;

    @Column(name = "PREVIOUS_EXP", nullable = false)
    String previousExp;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINING_DATE", nullable = false)
    Date joiningDate;

    @Column(name = "EXPERIENCE_GAPS", nullable = false)
    Long experienceGaps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<role> role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillId")
    private Collection<Skill> primarySkill;

    @OneToOne
    @JoinColumn(name="ACADEMICS_ID")
    private Academics academics;

    @OneToOne
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    @OneToOne
    @JoinColumn(name="REGION_ID")
    private Region region;

    @OneToOne
    @JoinColumn(name="LOCATION_ID")
    private Location location;

    @OneToOne
    @JoinColumn(name="BILLABLE_STATUS_ID")
    private BillableStatus billableStatus;

    @OneToOne
    @JoinColumn(name="SERVICE_LINE_ID")
    private ServiceLine serviceLine;

    @Column(name = "ACTIVITY_NAME", nullable = false)
    String activityName;

    @OneToOne
    @JoinColumn(name="PROJECT_TAGGING_ID")
    private ProjectTagging ProjectTagging;

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

}
