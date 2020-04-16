package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Region {
    @Id
    @Column(name="REGION_ID")
    Long regionId;

    public Region(){

    }

    @Column(name="REGION_NAME",nullable = false)
    String regionName;

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

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;
}
