package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROJECT_ID")
    Long projectId;

    @Column(name="PROJECT_NAME",nullable = false)
    String projectName;

    @Temporal(TemporalType.DATE)
    @Column(name = "PROJECT_START_DATE", nullable = false)
    Date projectStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "PROJECT_END_DATE", nullable = false)
    Date projectEndDate;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "SERVICE_LINE_ID")
    private ServiceLine serviceLine;

    @ManyToMany
    @JoinTable(name = "project_service_type",
            joinColumns = {@JoinColumn(name = "PROJECT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SERVICE_TYPE_ID")})
    private Collection<ServiceType> serviceTypes;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_TYPE_ID")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
     private Product product;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @Column(name="REMARK",nullable = false)
    String remark;

    @JsonIgnore
    @Column(name="INS_USER")
    Long insUser;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="INS_DATE")
    Date insDate;

    @JsonIgnore
    @Column(name="LAST_UPDATE_USER")
    Long lastUpdateUser;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATE_DATE")
    Date lastUpdateDate;

   /* @Column(name="status")
    String responseStatus;*/

   /* @Column(name="code")
    Integer responseCode;*/
}
