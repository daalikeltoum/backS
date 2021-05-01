package com.example.newprojectoption.service;


import com.example.newprojectoption.bean.Professeur;
import com.example.newprojectoption.dao.ProfesseurDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurDao professeurDao;


    public Professeur findByCin(String cin) {
        return professeurDao.findByCin(cin);
    }

    public Professeur findByCodeEmploye(String codeEmploye) {
        return professeurDao.findByCodeEmploye(codeEmploye);
    }

    public List<Professeur> findByNomAndPrenom(String nom, String prenon) {
        return professeurDao.findByNomAndPrenom(nom, prenon);
    }

    public int save (Professeur professeur){
        if(findByCin(professeur.getCin())!=null)
            return -1;
        else{
            professeurDao.save(professeur);
            return 1;
        }
    }

    @Transactional
    public int deleteByCodeEmploye(String codeEmploye) {
        return professeurDao.deleteByCodeEmploye(codeEmploye);
    }

    public List<Professeur> findAll() {
        return professeurDao.findAll();
    }
}
