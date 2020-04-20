package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Skill {
    @Id
    @Column(name = "SKILL_ID")
    Long skillId;

    @Column(name = "SKILL_NAME", nullable = false)
    String skillName;

    @JsonIgnore
    @Column(name = "INS_USER", nullable = false)
    Long insUser;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "INS_DATE", nullable = false)
    Date insDate;

    @JsonIgnore
    @Column(name = "LAST_UPDATE_USER", nullable = false)
    Long lastUpdateUser;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    Date lastUpdateDate;
}
