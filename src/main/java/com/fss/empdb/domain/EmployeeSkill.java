package com.fss.empdb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Column(name = "EMPLOYEE_DEFINITE_ROLE_SQID")
	private Long employeeSkillSqId;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_SQID")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "SKILL_ID")
	private Skill skill;
	
	@Column(name = "CERTIFIED")
	private Long certified;
	
	@Column(name = "USED_IN_PROJECT")
	private Long usedInProject;
	
	@Column(name = "INS_USER", nullable = true)
    private Long insUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", nullable = true)
    private Date insDate;

}
