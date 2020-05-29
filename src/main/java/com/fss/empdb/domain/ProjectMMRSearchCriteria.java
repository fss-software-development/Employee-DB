package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProjectMMRSearchCriteria {
    private String  reportType;
    private Long    financialYear;
    private Region[] region;
    private Account[] account;
    private Project[] project;
    private ServiceLine[] serviceLine;
    private ServiceType[] serviceTypes;
    private Product[]  products;
}
