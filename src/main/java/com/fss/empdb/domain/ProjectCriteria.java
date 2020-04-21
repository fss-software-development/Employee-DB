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
public class ProjectCriteria {

    Long projectId;
    String projectName;
    String projectManager;
    String projectStatus;
    private Department[] department;
    private Region[] region;
    private Account[] account;
    private ProjectTagging[] projectTagging;
    Long insUser;
    Date insDate;
    Long lastUpdateUser;
    Date lastUpdateDate;

}


