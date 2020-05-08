
package com.fss.empdb.domain;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    Long productId;

    @Column(name = "PRODUCT_TYPE_ID")
    Long productTypeId;

//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_TYPE_ID")
//    private ProductType productType;

    @Column(name = "PRODUCT_NAME", nullable = false)
    String productName;
}