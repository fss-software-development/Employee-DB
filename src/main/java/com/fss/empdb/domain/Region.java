package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="REGION_ID")
    Long regionId;

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

    @OneToMany(mappedBy = "region",  cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

//    @OneToMany(mappedBy = "region",  cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Account> accounts;
}
