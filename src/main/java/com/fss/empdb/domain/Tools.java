package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tools {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOOL_ID")
    Long toolId;

    @Column(name = "TOOL_NAME", nullable = false)
    String toolName;

}
