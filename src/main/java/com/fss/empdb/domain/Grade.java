package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GRADE_ID")
    Long gradeId;

    public Grade() {

    }

    @Column(name = "GRADE_NAME", nullable = false)
    String gradeName;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

}
