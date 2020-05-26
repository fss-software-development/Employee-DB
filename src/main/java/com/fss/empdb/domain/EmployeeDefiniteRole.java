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
@Table(name = "EMPLOYEE_DEFINITE_ROLE")
public class EmployeeDefiniteRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_DEFINITE_ROLE_SQID")
	private Long employeeDefiniteRoleSqId;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_SQID")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

}
