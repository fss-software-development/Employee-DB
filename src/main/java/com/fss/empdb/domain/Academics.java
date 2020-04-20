package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Academics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACADEMICS_ID")
    Long academicsId;

    public Academics() {

    }

    @Column(name = "ACADEMICS_NAME", nullable = false)
    String academicsName;

    @JsonIgnore
    @OneToMany(mappedBy = "academics", cascade = CascadeType.ALL)
    private List<Employee> employees;


}

