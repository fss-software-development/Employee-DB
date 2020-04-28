package com.fss.empdb.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.beans.DefaultProperty;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.dom4j.QName;
import org.hibernate.annotations.*;
import org.springframework.boot.context.properties.bind.DefaultValue;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@XmlRootElement
@EqualsAndHashCode
@ToString
@Transactional
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_SQID")
    Long employeeSqId;

    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    //    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "EMPLOYEE_NAME", nullable = false)
    String employeeName;

    @Column(name = "MOBILE_NUM", nullable = false)
    Long mobileNum;

    @Column(name = "EMAIL_ID", nullable = false)
    String emailId;

    @ManyToOne
    @JoinColumn(name = "GRADE_ID")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "DESIGNATION_ID")
    private Designation designation;

    @Column(name = "REPORTING_MANAGER", nullable = false)
    String reportingManager;

    @Column(name = "PREVIOUS_EXP", nullable = false)
    Long previousExp;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINING_DATE", nullable = false)
    Date joiningDate;

    @ManyToOne
    @JoinColumn(name = "BILLABLE_STATUS_ID")
    private BillableStatus billableStatus;

    @ManyToOne
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


    @ManyToOne
    @JoinColumn(name = "ACADEMICS_ID")
    private Academics academics;

    @ManyToMany
    @JoinTable(name = "employee_project",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_SQID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID")})
    private Collection<Project> projects;

    @ManyToMany
    @JoinTable(name = "employee_skill",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_SQID")},
            inverseJoinColumns = {@JoinColumn(name = "SKILL_ID")})
    private Collection<Skill> skills;

    @ManyToMany
    @JoinTable(name = "employee_tools",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_SQID")},
            inverseJoinColumns = {@JoinColumn(name = "TOOL_ID")})
    private Collection<Tools> tools;

    @ManyToMany
    @JoinTable(name = "employee_definite_role",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_SQID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Collection<Role> definiteRole;

    @ManyToMany
    @JoinTable(name = "employee_possible_role",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_SQID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
   private Collection<Role> possibleRole;

    @Column(name = "INS_USER", nullable = true)
    private Long insUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "INS_DATE", nullable = true)
    private Date insDate;

    @Column(name = "LAST_UPDATE_USER", nullable = true)
    private Long lastUpdateUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATE_DATE", nullable = true)
    private Date lastUpdateDate;

    @JsonIgnore
    @Column(name = "PRIMARY_SKILL")
    Long primarySkillId;

}
