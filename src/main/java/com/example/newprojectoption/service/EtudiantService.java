package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.dao.EtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EtudiantService {
    @Autowired
    private EtudiantDao etudiantDao;
    @Autowired
    private MyOptionService myOptionService;

    public Etudiant findByCne(String cne) {
        return etudiantDao.findByCne(cne);
    }

    public List<Etudiant> findByNomAndPrenom(String nom, String prenon) {
        return etudiantDao.findByNomAndPrenom(nom, prenon);
    }
    @Transactional
    public int deleteByCne(String cne) {
        return etudiantDao.deleteByCne(cne);
    }

    public List<Etudiant> findAll() {
        return etudiantDao.findAll();
    }

    public List<Etudiant> findByMyOptioncode(String code) {
        return etudiantDao.findByMyOptionCode(code);
    }
    public int save(Etudiant etudiant){

        if(findByCne(etudiant.getCne())!=null){
            return -1;
        }
        MyOption myOption=myOptionService.findByCode(etudiant.getMyOption().getCode());
        etudiant.setMyOption(myOption);
         if(myOption==null)
             return -2;

         else{
           etudiant.setMyOption(myOption);
           etudiantDao.save(etudiant);
           return 1;
        }
    }




    }


