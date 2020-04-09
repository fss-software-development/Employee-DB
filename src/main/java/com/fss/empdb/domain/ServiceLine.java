package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "service_line")
public class ServiceLine {
    @Id
    @Column(name = "SERVICE_LINE_ID")
    Long serviceLineId;

    public ServiceLine() {

    }

    @Column(name = "SERVICE_LINE_NAME", nullable = false)
    String serviceLineName;
}

