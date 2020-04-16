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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ACCOUNT_ID")
    Long accountId;

    public Account(){

    }

    @Column(name="ACCOUNT_NAME",nullable = false)
    String accountName;

//    @JsonIgnore
    @Column(name="INS_USER")
    Long insUser;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
//    private Collection<Region> region;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "REGION_ID")
    private Region region;

//    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="INS_DATE")
    Date insDate;

//    @JsonIgnore
    @Column(name="LAST_UPDATE_USER")
    Long lastUpdateUser;

//    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATE_DATE")
    Date lastUpdateDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;



}
