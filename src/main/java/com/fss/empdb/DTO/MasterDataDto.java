package com.fss.empdb.DTO;

import com.fss.empdb.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class MasterDataDto {

	private Map<String, Department> departments;

	private Map<String, Account> accounts;

	private Map<String, Region> regions;

	private Map<String, Location> locations;

	private Map<String, Grade> grades;

	private Map<String, Designation> designations;

	private Map<String, BillableStatus> billableStatus;

	private Map<String, ServiceLine> serviceLines;

	private Map<String, ProjectTagging> projectTaggings;

	private Map<String, Academics> academics;

	private Map<String, Project> projects;

	private Map<String, Skill> skills;

	private Map<String, Tools> tools;

	private Map<String, Role> roles;

}
