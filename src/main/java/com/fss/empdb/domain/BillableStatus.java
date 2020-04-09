package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

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

    @JsonIgnore
    @OneToOne(mappedBy="billableStatus")
    private Employee employee;

}
