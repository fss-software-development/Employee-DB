package com.fss.empdb.domain;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_TYPE_ID")
    Long productTypeId;

    @Column(name = "PRODUCT_TYPE_NAME", nullable = false)
    String productTypeName;
}