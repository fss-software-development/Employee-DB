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
@ToString
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYEE_SQID")
    Long employeeSqId;

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
//    @ManyToOne
//    @JoinColumn(name = "DEPARTMENT_ID")
//    private Collection<Department> department;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "REGION_ID")
    private Region region;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "EMPLOYEE_NAME", nullable = false)
    String employeeName;

    @Column(name = "MOBILE_NUM", nullable = false)
    Long mobileNum;

    @Column(name = "EMAIL_ID", nullable = false)
    String emailId;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "GRADE_ID")
    private Grade grade;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DESIGNATION_ID")
    private Designation designation;

    @Column(name = "REPORTING_MANAGER", nullable = false)
    String reportingManager;

    @Column(name = "PREVIOUS_EXP", nullable = false)
    Long previousExp;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINING_DATE", nullable = false)
    Date joiningDate;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "BILLABLE_STATUS_ID")
    private BillableStatus billableStatus;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SERVICE_LINE_ID")
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
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ACADEMICS_ID")
    private Academics academics;

    @Column(name = "INS_USER",nullable = true)
    private Long insUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "INS_DATE",nullable = true)
    private Date insDate;

    @Column(name = "LAST_UPDATE_USER",nullable = true)
    private Long lastUpdateUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATE_DATE",nullable = true)
    private Date lastUpdateDate;

    @JsonIgnore
    @Column(name="PRIMARY_SKILL")
    Long primarySkillId;


}
