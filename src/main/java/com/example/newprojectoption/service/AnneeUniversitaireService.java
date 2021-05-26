package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.AnneeUniversitaire;
import com.example.newprojectoption.dao.AnneeUniversitaireDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnneeUniversitaireService {
    @Autowired
    private AnneeUniversitaireDao anneeUniversitaireDao;

    public List<AnneeUniversitaire> findAll() {
        return anneeUniversitaireDao.findAll();
    }

    public AnneeUniversitaire findByLibelle(String libelle) {
        return anneeUniversitaireDao.findByLibelle(libelle);
    }

    public AnneeUniversitaire findByAnneeOne(Long annee1) {
        return anneeUniversitaireDao.findByAnneeOne(annee1);
    }
}
