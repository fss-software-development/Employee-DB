package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "employee_skill")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_SKILL_SQID")
	private Long employeeSkillSqId;

	@Getter(AccessLevel.NONE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_SQID")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "SKILL_ID")
	private Skill skill;
	
	@Column(name = "CERTIFIED")
	private Long certified;

	@JsonIgnore
	@Column(name = "USED_IN_PROJECT")
	private Long usedInProject;

	@JsonIgnore
	@Column(name = "INS_USER")
    private Long insUser;

	@JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", nullable = true)
    private Date insDate;

}
