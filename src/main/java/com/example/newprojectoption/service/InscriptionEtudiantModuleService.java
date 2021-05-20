package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.InscriptionEtudiantModule;
import com.example.newprojectoption.dao.InscriptionEtudiantModuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InscriptionEtudiantModuleService {
    @Autowired
    private InscriptionEtudiantModuleDao inscriptionEtudiantModuleDao;

    public void save(InscriptionEtudiantModule inscriptionEtudiantModule){
       inscriptionEtudiantModuleDao.save(inscriptionEtudiantModule);
    }

}
