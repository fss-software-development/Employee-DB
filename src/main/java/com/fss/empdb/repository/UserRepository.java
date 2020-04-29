package com.fss.empdb.repository;

import com.fss.empdb.domain.Academics;
import com.fss.empdb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    //User findById(int );
    //User findByName(String username);
    //User findById(int userid);
}
