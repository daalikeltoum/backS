package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Userr,Long> {
    Userr findByUsername(String username);

}
