package com.fss.empdb.repository;

import com.fss.empdb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

//    @Query("SELECT u FROM Product u WHERE u.productTypeId = ?1")
//    List<Product>  findByProductType(Long productTypeId);

}
