package com.example.newprojectoption.service;


import com.example.newprojectoption.bean.Filliere;
import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.dao.MyOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyOptionService {


    @Autowired
    private MyOptionDao myOptionDao;
    @Autowired
    private PonderationService ponderationService;
    @Autowired
    private FilliereService filliereService;

    public MyOption findByPonderationCode(String code) {
        return myOptionDao.findByPonderationCode(code);
    }
    public MyOption findByCode(String code) {
        return myOptionDao.findByCode(code);
    }

    @Transactional
    public int deleteByCode(String code) {
        return myOptionDao.deleteByCode(code);
    }

    public List<MyOption> findAll() {
        return myOptionDao.findAll();
    }

    public int save(MyOption myOption) {
        if(findByCode(myOption.getCode())!=null)
            return -1;
        else{
            Filliere filliere=filliereService.findByCode(myOption.getFilliere().getCode());
            myOption.setFilliere(filliere);
            myOptionDao.save(myOption);
            return 1;
        }
    }

    public List<MyOption> findByFilliereCode(String code) {

        return myOptionDao.findByFilliereCode(code);
    }
    public void update(MyOption myOption){
      /*ponderationService.save(myOption.getPonderation());*/
        myOption.setFilliere(myOption.getFilliere());
        myOption.setPonderation(myOption.getPonderation());
        myOptionDao.save(myOption);
    }
}
