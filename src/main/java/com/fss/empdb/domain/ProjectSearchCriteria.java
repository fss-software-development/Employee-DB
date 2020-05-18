package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
public class ProjectSearchCriteria {

    String projectName;
    private Product[]  products;
    private Region[] region;
    private Account[] account;
    private ServiceLine[] serviceLine;
    private ServiceType[] serviceTypes;
    private Status[]  status;
}



