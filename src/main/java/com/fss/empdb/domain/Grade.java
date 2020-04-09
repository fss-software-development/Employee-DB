package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Grade {
    @Id
    @Column(name = "GRADE_ID")
    Long gradeId;

    public Grade() {

    }

    @Column(name = "GRADE_NAME", nullable = false)
    String gradeName;
}
