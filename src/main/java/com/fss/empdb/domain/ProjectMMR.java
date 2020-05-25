package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="project_mmr")
@Transactional
public class ProjectMMR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROJECT_MMR_ID")
    Long projectMmrId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @JsonIgnore
    @Column(name = "FINANCIAL_YEAR", nullable = false)
    Long year;

    @Column(name = "MONTH_OF_YEAR", nullable = false)
    String month;

    @Column(name = "FORECASTED_VALUE", nullable = false)
    Long forecastedValue;

    @Column(name = "BUDGETED_VALUE", nullable = false)
    Long budgetedValue;

    @Column(name = "ACTUAL_VALUE", nullable = false)
    Long actualValue;

    @Column(name="REMARKS",nullable = false)
    String remarks;

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

}
