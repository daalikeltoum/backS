package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.TestExcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestExcelDao  extends JpaRepository<TestExcel, Long> {
    TestExcel findByTitle(String title);
}
