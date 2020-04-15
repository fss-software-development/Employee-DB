package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "billable_status")
public class BillableStatus {
    @Id
    @Column(name = "BILLABLE_STATUS_ID")
    Long billableStatusId;

    public BillableStatus() {

    }

    @Column(name = "BILLABLE_STATUS", nullable = false)
    String billableStatus;

    @OneToMany(mappedBy = "billableStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;

}
