package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.Semestre;
import com.example.newprojectoption.bean.TestExcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestExcelDao  extends JpaRepository<TestExcel, Long> {
    TestExcel findByTitle(String title);
}
