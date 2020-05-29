package com.fss.empdb.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
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
@Builder
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_SQID")
    Long employeeSqId;

//    @NotEmpty(message = "{employee.employeeId}")
    @Column(name = "EMPLOYEE_ID")
    String employeeId;

    @Column(name = "EMPLOYEE_NAME", nullable = false)
    String employeeName;

    @Column(name = "MOBILE_NUM", nullable = false)
    Long mobileNum;

    @Column(name = "EMAIL_ID", nullable = false)
    String emailId;

    @Column(name = "REPORTING_MANAGER", nullable = false)
    String reportingManager;

    @Column(name = "ACTIVITY_NAME", nullable = false)
    String activityName;

    @Temporal(TemporalType.DATE)
    @Column(name = "JOINING_DATE", nullable = false)
    Date joiningDate;

    @Column(name = "PREVIOUS_EXP", nullable = false)
    Long previousExp;

    @Column(name = "EXPERIENCE_GAPS", nullable = false)
    Long experienceGaps;

    @Column(name = "EXPERIENCE_CURRENT_ROLE", nullable = false)
    Long  experienceCurrentRole;

    @Column(name = "TOTAL_EXPERIENCE", nullable = false)
    Long totalExperience;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "GRADE_ID")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "DESIGNATION_ID")
    private Designation designation;

    @ManyToOne
    @JoinColumn(name = "BILLABLE_STATUS_ID")
    private BillableStatus billableStatus;

    @ManyToOne
    @JoinColumn(name = "SERVICE_LINE_ID")
    private ServiceLine serviceLine;

    @ManyToOne
    @JoinColumn(name = "ACADEMICS_ID")
    private Academics academics;

    @ManyToMany
    @JoinTable(name = "employee_project",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_SQID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID")})
    private Collection<Project> projects;

//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private Set<EmployeeProject> employeeProjects;

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

    @ManyToOne
    @JoinColumn(name = "PROJECT_TAGGING_ID")
    private ProjectTagging projectTagging;


    @Column(name = "INS_USER", nullable = true)
    private Long insUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", nullable = true)
    private Date insDate;

    @Column(name = "LAST_UPDATE_USER", nullable = true)
    private Long lastUpdateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_DATE", nullable = true)
    private Date lastUpdateDate;

    @Lob
    @Column(name = "CV_DOCUMENT", nullable = true)
    private byte[] cvDocument;

}
