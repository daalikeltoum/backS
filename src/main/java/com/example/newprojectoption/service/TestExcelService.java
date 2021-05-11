package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.Semestre;
import com.example.newprojectoption.bean.TestExcel;
import com.example.newprojectoption.dao.TestExcelDao;
import com.example.newprojectoption.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Service
public class TestExcelService {


    public int save (TestExcel testExcel){
            testExcelDao.save(testExcel);
            return 1;
    }


    public List<TestExcel> findAll() {
        return testExcelDao.findAll();
    }

    public TestExcel findByTitle(String title) {
        return testExcelDao.findByTitle(title);
    }

    @Autowired
    TestExcelDao testExcelDao;
    public ByteArrayInputStream load() {
        List<TestExcel> tutorials = testExcelDao.findAll();

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
        return in;
    }
}
