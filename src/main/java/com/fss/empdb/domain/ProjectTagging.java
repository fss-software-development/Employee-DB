package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "project_tagging")
public class ProjectTagging {
    @Id
    @Column(name = "PROJECT_TAGGING_ID")
    Long projectTaggingId;


    @Column(name = "PROJECT_TAGGING", nullable = false)
    String projectTaggingName;


}


