package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GRADE_ID")
    Long gradeId;

    @Column(name = "GRADE_NAME", nullable = false)
    String gradeName;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

}
