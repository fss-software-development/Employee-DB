package com.fss.empdb.DTO;

import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.ProjectMMR;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class ProjectMMRDto {

    Project project;
    Long financialYear;
    List<ProjectMMR> mmr;

}