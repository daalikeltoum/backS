package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.MyModule;
import com.example.newprojectoption.dao.MyModuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyModuleService {
    @Autowired
    private MyModuleDao myModuleDao;

    public MyModule findByCode(String code) {
        return myModuleDao.findByCode(code);
    }

    public MyModule findByLibelle(String libelle) {
        return myModuleDao.findByLibelle(libelle);
    }

    public int  save (MyModule myModule){
        if(findByCode(myModule.getCode())!=null)
            return -1;
        else{
            myModuleDao.save(myModule);
            return 1;
        }
    }
    @Transactional
    public int deleteByCode(String code) {

        return myModuleDao.deleteByCode(code);
    }

    public List<MyModule> findAll() {
        return myModuleDao.findAll();
    }

    public List<MyModule> findByMyOptionCode(String code) {
        return myModuleDao.findByMyOptionCode(code);
    }
}
