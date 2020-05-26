package com.fss.empdb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "EMPLOYEE_PROJECT")
public class EmployeeProject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_PROJECT_SQID")
	private Long employeeProjectSqId;
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_SQID")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "INS_USER", nullable = true)
    private Long insUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", nullable = true)
    private Date insDate;

}
