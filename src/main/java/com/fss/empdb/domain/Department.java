package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Department {
    @Id
    @Column(name="DEPARTMENT_ID")
    Long departmentId;

    public Department(){

    }

    @Column(name="DEPARTMENT_NAME",nullable = false)
    String departmentName;

    @Column(name="DEPARTMENT_HEAD",nullable = false)
    String departmentHead;

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
