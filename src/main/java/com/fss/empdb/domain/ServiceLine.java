package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_line")
public class ServiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SERVICE_LINE_ID")
    Long serviceLineId;

    @Column(name = "SERVICE_LINE_NAME", nullable = false)
    String serviceLineName;

//    @OneToMany(mappedBy = "serviceLine", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Employee> employees;
}

