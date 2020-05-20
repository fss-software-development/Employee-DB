package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ProjectMMRDto {

    Project project;
    Long financialYear;
    Map<String, ProjectMMR> mmr;

}