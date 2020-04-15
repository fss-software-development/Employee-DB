package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Academics {
    @Id
    @Column(name = "ACADEMICS_ID")
    Long academicsId;

    public Academics() {

    }

    @Column(name = "ACADEMICS_NAME", nullable = false)
    String academicsName;

    @OneToMany(mappedBy = "academics", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;

}

