package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.bean.TypeModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeModuleDao extends JpaRepository<TypeModule,Long> {
    TypeModule findByCode(String code);
}