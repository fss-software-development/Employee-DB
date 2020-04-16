package com.fss.empdb.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CollectionType;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@XmlRootElement
@EqualsAndHashCode

public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_SQID")
    Long employeeSqId;

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
//    @ManyToOne
//    @JoinColumn(name = "DEPARTMENT_ID")
//    private Collection<Department> department;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "DEPARTMENT_ID"), name = "DEPARTMENT_ID")
    private Department department;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "ACCOUNT_ID"), name = "ACCOUNT_ID")
    private Account account;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "REGION_ID"), name = "REGION_ID")
    private Region region;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "LOCATION_ID"), name = "LOCATION_ID")
    private Location location;

    @Column(name = "EMPLOYEE_NAME", nullable = false)
    String employeeName;

    @Column(name = "MOBILE_NUM", nullable = false)
    Long mobileNum;

    @Column(name = "EMAIL_ID", nullable = false)
    String emailId;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "GRADE_ID"), name = "GRADE_ID")
    private Grade grade;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "DESIGNATION_ID"), name = "DESIGNATION_ID")
    private Designation designation;

    @Column(name = "REPORTING_MANAGER", nullable = false)
    String reportingManager;

    @Column(name = "PREVIOUS_EXP", nullable = false)
    Long previousExp;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINING_DATE", nullable = false)
    Date joiningDate;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "BILLABLE_STATUS_ID"), name = "BILLABLE_STATUS_ID")
    private BillableStatus billableStatus;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "SERVICE_LINE_ID"), name = "SERVICE_LINE_ID")
    private ServiceLine serviceLine;

    @Column(name = "ACTIVITY_NAME", nullable = false)
    String activityName;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillId")
//    private Collection<Skill> primarySkill;

    @Column(name = "EXPERIENCE_GAPS", nullable = false)
    Long experienceGaps;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
//    private Collection<Role> role;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "ACADEMICS_ID"), name = "ACADEMICS_ID")
    private Academics academics;

    @Column(name = "INS_USER", nullable = false)
    Long insUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", nullable = false)
    Date insDate;

    @Column(name = "LAST_UPDATE_USER", nullable = false)
    Long lastUpdateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    Date lastUpdateDate;

    @JsonIgnore
    @Column(name="PRIMARY_SKILL",nullable = false)
    Long primarySkillId;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeSqId=" + employeeSqId +
                ", employeeId=" + employeeId +
                ", department=" + department +
                ", account=" + account +
                ", region=" + region +
                ", location=" + location +
                ", employeeName='" + employeeName + '\'' +
                ", mobileNum=" + mobileNum +
                ", emailId='" + emailId + '\'' +
                ", grade=" + grade +
                ", designation=" + designation +
                ", reportingManager='" + reportingManager + '\'' +
                ", previousExp=" + previousExp +
                ", joiningDate=" + joiningDate +
                ", billableStatus=" + billableStatus +
                ", serviceLine=" + serviceLine +
                ", activityName='" + activityName + '\'' +
                ", experienceGaps=" + experienceGaps +
                ", academics=" + academics +
                ", insUser=" + insUser +
                ", insDate=" + insDate +
                ", lastUpdateUser=" + lastUpdateUser +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }

    //
//    @JsonIgnore
//    @Column(name="DESIGNATION_ID",nullable = false)
//    Long designationId;
//
//    @JsonIgnore
//    @Column(name = "DEPARTMENT_ID", nullable = false)
//    private Long departmentId;
//
//    @JsonIgnore
//    @Column(name = "REGION_Id", nullable = false)
//    private Long regionId;
//
//    @JsonIgnore
//    @Column(name="ACCOUNT_ID",nullable = false)
//    Long accountId;
//
//    @JsonIgnore
//    @Column(name="SERVICE_LINE_ID",nullable = false)
//    Long serviceLineId;
//
//    @JsonIgnore
//    @Column(name="BILLABLE_STATUS_ID",nullable = false)
//    Long billableStatusId;
//
//    @JsonIgnore
//    @Column(name="LOCATION_ID",nullable = false)
//    Long locationId;
//
//    @JsonIgnore
//    @Column(name="GRADE_ID",nullable = false)
//    Long gradeId;
//
//    @JsonIgnore
//    @Column(name="ACADEMICS_ID",nullable = false)
//    Long academicId;

//    @Override
//    public String toString() {
//        return "Employee{" +
//                "employeeSqId=" + employeeSqId +
//                ", employeeId=" + employeeId +
//                ", department=" + department +
//                ", account=" + account +
//                ", region=" + region +
//                ", location=" + location +
//                ", employeeName='" + employeeName + '\'' +
//                ", mobileNum=" + mobileNum +
//                ", emailId='" + emailId + '\'' +
//                ", grade=" + grade +
//                ", designation=" + designation +
//                ", reportingManager='" + reportingManager + '\'' +
//                ", previousExp=" + previousExp +
//                ", joiningDate=" + joiningDate +
//                ", billableStatus=" + billableStatus +
//                ", serviceLine=" + serviceLine +
//                ", activityName='" + activityName + '\'' +
//                ", primarySkill=" + primarySkill +
//                ", experienceGaps=" + experienceGaps +
//                ", academics=" + academics +
//                ", insUser=" + insUser +
//                ", insDate=" + insDate +
//                ", lastUpdateUser=" + lastUpdateUser +
//                ", lastUpdateDate=" + lastUpdateDate +
//                ", designationId=" + designationId +
//                ", departmentId=" + departmentId +
//                ", regionId=" + regionId +
//                ", accountId=" + accountId +
//                ", serviceLineId=" + serviceLineId +
//                ", billableStatusId=" + billableStatusId +
//                ", locationId=" + locationId +
//                ", gradeId=" + gradeId +
//                ", academicId=" + academicId +
//                ", primarySkillId=" + primarySkillId +
//                '}';
//    }
//
//    @JsonIgnore
//    @Column(name="PRIMARY_SKILL",nullable = false)
//    Long primarySkillId;

}
