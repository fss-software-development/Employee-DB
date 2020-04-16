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
public class Designation {
    @Id
    @Column(name="DESIGNATION_ID")
    Long designationId;

    public Designation(){

    }

    @Column(name="DESIGNATION_NAME",nullable = false)
    String designationName;

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


    @OneToMany(mappedBy = "designation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Designation{" +
                "designationId=" + designationId +
                ", designationName='" + designationName + '\'' +
                ", insUser=" + insUser +
                ", insDate=" + insDate +
                ", lastUpdateUser=" + lastUpdateUser +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }


}
