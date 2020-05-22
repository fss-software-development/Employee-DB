package com.fss.empdb.domain;

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