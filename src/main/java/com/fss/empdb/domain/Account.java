package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    Long accountId;

    @Column(name="ACCOUNT_NAME",nullable = false)
    String accountName;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "REGION_ID",nullable = false)
    private Region region;

    @JsonIgnore
    @Column(name="INS_USER")
    Long insUser;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="INS_DATE")
    Date insDate;

    @JsonIgnore
    @Column(name="LAST_UPDATE_USER")
    Long lastUpdateUser;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATE_DATE")
    Date lastUpdateDate;

//    @JsonIgnore
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
//    private List<Employee> employees;



}
