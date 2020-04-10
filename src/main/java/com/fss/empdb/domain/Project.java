package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @Column(name="PROJECT_ID")
    Long projectId;

    public Project(){

    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
    private Collection<Department> department;

    @Column(name="PROJECT_NAME",nullable = false)
    String projectName;

    @Column(name="PROJECT_MANAGER",nullable = false)
    String projectManager;

    @Column(name="PROJECT_STATUS",nullable = false)
    String projectStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Account> account;

    @JsonIgnore
    @Column(name="INS_USER",nullable = false)
    Long insUser;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="INS_DATE",nullable = false)
    Date insDate;

    @JsonIgnore
    @Column(name="LAST_UPDATE_USER",nullable = false)
    Long lastUpdateUser;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATE_DATE",nullable = false)
    Date lastUpdateDate;

}
