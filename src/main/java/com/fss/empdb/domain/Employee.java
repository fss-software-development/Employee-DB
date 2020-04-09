package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity

public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

    public Employee() {
    }

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicsId")
    private Collection<Academics> academics;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectTaggingId")
    private Collection<ProjectTagging> ProjectTagging;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "definiteRoleId")
    private Collection<DefiniteRole> DefiniteRole;

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
