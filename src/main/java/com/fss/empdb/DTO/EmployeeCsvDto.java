package com.fss.empdb.DTO;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCsvDto {

	public EmployeeCsvDto(Map<String, Object> entry){

		this.employeeId = StringUtils.trimToNull(String.valueOf(entry.get("Employee Code/Id")));

		this.employeeName = StringUtils.trimToNull(String.valueOf(entry.get("Employee Name")));

		this.mobileNumber = StringUtils.trimToNull(String.valueOf(entry.get("Mobile Number")));

		this.emailID = StringUtils.trimToNull(String.valueOf(entry.get("Employee email ID")));

		this.designation = StringUtils.trimToNull(String.valueOf(entry.get("Designation")));

		this.departmentName = StringUtils.trimToNull(String.valueOf(entry.get("Department Name")));

		this.region = StringUtils.trimToNull(String.valueOf(entry.get("Region")));

		this.account = StringUtils.trimToNull(String.valueOf(entry.get("Account")));

		this.serviceLine = StringUtils.trimToNull(String.valueOf(entry.get("Service Line")));

		this.reportingHead = StringUtils.trimToNull(String.valueOf(entry.get("Reporting Head")));

		this.previousExperience = StringUtils.trimToNull(String.valueOf(entry.get("Previous Experience")));

		this.experienceCurrentRole = StringUtils.trimToNull(String.valueOf(entry.get("Experience in Current Role")));

		this.experienceGaps = StringUtils.trimToNull(String.valueOf(entry.get("Experience Gaps")));

		this.totalExperience = StringUtils.trimToNull(String.valueOf(entry.get("Total Experience")));

		this.activityName = StringUtils.trimToNull(String.valueOf(entry.get("Activity Name")));

		this.workingLocation = StringUtils.trimToNull(String.valueOf(entry.get("Working Location")));

		this.grade = StringUtils.trimToNull(String.valueOf(entry.get("Grade")));

		this.educationalQualification = StringUtils.trimToNull(String.valueOf(entry.get("Educational Qualification/Academics")));

		this.billableStatus = StringUtils.trimToNull(String.valueOf(entry.get("Billable Status")));

		this.doj = StringUtils.trimToNull(String.valueOf(entry.get("DOJ")));

		this.defineRole = StringUtils.trimToNull(String.valueOf(entry.get("Definite Role [Definite Roles]")));

		this.possibleRole = StringUtils.trimToNull(String.valueOf(entry.get("Possible Role [Possible Roles]")));

		this.tools = StringUtils.trimToNull(String.valueOf(entry.get("Tools [Tools List]")));

		this.projectTagging = StringUtils.trimToNull(String.valueOf(entry.get("Project Tagging")));

		this.profile = StringUtils.trimToNull(String.valueOf(entry.get("Profile")));

		this.projects = StringUtils.trimToNull(String.valueOf(entry.get("Projects [Projects]")));

		this.skills = StringUtils.trimToNull(String.valueOf(entry.get("Skills [Skills]")));


		/*Object[] keys = entry.keySet().toArray();

		// Map project details
		this.projects = new HashMap<>();
		this.projects.put(keys[26].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[26].toString()))));
		this.projects.put(keys[27].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[27].toString()))));
		this.projects.put(keys[28].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[28].toString()))));
		this.projects.put(keys[29].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[29].toString()))));
		this.projects.put(keys[30].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[30].toString()))));

		// Map employee skills
		this.skills = new HashMap<>();
		this.skills.put(keys[31].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[31].toString()))));
		this.skills.put(keys[32].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[32].toString()))));
		this.skills.put(keys[33].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[33].toString()))));
		this.skills.put(keys[34].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[34].toString()))));
		this.skills.put(keys[35].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[35].toString()))));
		this.skills.put(keys[36].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[36].toString()))));
		this.skills.put(keys[37].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[37].toString()))));
		this.skills.put(keys[38].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[38].toString()))));
		this.skills.put(keys[39].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[39].toString()))));
		this.skills.put(keys[40].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[40].toString()))));
		this.skills.put(keys[41].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[41].toString()))));
		this.skills.put(keys[42].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[42].toString()))));
		this.skills.put(keys[43].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[43].toString()))));
		this.skills.put(keys[44].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[44].toString()))));
		this.skills.put(keys[45].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[45].toString()))));
		this.skills.put(keys[46].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[46].toString()))));
		this.skills.put(keys[47].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[47].toString()))));
		this.skills.put(keys[48].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[48].toString()))));
		this.skills.put(keys[49].toString(), StringUtils.trimToNull(String.valueOf(entry.get(keys[49].toString()))));*/
	}

	private String employeeId;

	private String employeeName;

	private String mobileNumber;

	private String emailID;

	private String designation;

	private String departmentName;

	private String region;

	private String account;

	private String serviceLine;

	private String reportingHead;

	private String previousExperience;

	private String experienceCurrentRole;

	private String experienceGaps;

	private String totalExperience;

	private String activityName;

	private String workingLocation;

	private String grade;

	private String educationalQualification;

	private String billableStatus;

	private String doj;

	private String defineRole;

	private String possibleRole;

	private String tools;

	private String projectTagging;

	private String profile;

	private String projects;

	private String skills;

//	private Map<String, String> projects;
//
//	private Map<String, String> skills;

}
