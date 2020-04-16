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
public class Location {
    @Id
    @Column(name="LOCATION_ID")
    Long locationId;

    public Location(){

    }

    @Column(name="LOCATION_NAME",nullable = false)
    String locationName;

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

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;
}
