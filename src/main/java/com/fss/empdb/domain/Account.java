package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @Column(name="ACCOUNT_ID")
    Long accountId;

    public Account(){

    }

    @Column(name="ACCOUNT_NAME",nullable = false)
    String accountName;

    @Column(name="INS_USER",nullable = false)
    Long insUser;

    @Temporal(TemporalType.DATE)
    @Column(name="INS_DATE",nullable = false)
    Date insDate;

    @Column(name="LAST_UPDATE_USER",nullable = false)
    Long lastUpdateUser;

    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATE_DATE",nullable = false)
    Date lastUpdateDate;
}
