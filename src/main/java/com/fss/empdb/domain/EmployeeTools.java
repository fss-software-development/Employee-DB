package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "EMPLOYEE_TOOLS")
public class EmployeeTools implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_TOOL_SQID")
	private Long employeeToolSqId;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_SQID")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "TOOL_ID")
	private Tools tools;

}
