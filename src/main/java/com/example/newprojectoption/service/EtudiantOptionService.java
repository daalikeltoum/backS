package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.dao.EtudiantDao;
import com.example.newprojectoption.dao.EtudiantOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EtudiantOptionService {
    public List<EtudiantOption> findByMyOptionCode(String code) {
        return etudiantOptionDao.findByMyOptionCode(code);
    }
    @Transactional
    public int deleteByEtudiantCne(String cne) {
        return etudiantOptionDao.deleteByEtudiantCne(cne);
    }
    @Transactional
    public int deleteByMyOptionCode(String code) {
        return etudiantOptionDao.deleteByMyOptionCode(code);
    }

    public EtudiantOption findByEtudiantCneAndMyOptionCode(String cne, String code) {
        return etudiantOptionDao.findByEtudiantCneAndMyOptionCode(cne, code);
    }

    public List<EtudiantOption> findByMyOptionCodeAndAnnee(String code, String annee) {
        return etudiantOptionDao.findByMyOptionCodeAndAnnee(code, annee);
    }

    public int save(EtudiantOption etudiantOption){
      if(findByEtudiantCneAndMyOptionCode(etudiantOption.getEtudiant().getCne(),etudiantOption.getMyOption().getCode())!=null){
          return -1;
      }
        Etudiant etudiant=etudiantService.findByCne(etudiantOption.getEtudiant().getCne());
        MyOption myOption=myOptionService.findByCode(etudiantOption.getMyOption().getCode());
        if(etudiant!=null){
            etudiantOption.setEtudiant(etudiant);
            etudiantOption.setMyOption(myOption);
            etudiantOptionDao.save(etudiantOption);

        }
        else {
            etudiantService.save(etudiantOption.getEtudiant());
            etudiantOption.setEtudiant(etudiantService.findByCne(etudiantOption.getEtudiant().getCne()));
            etudiantOption.setMyOption(myOption);
            etudiantOptionDao.save(etudiantOption);

        }
        return 1;
    }


    @Autowired
    private EtudiantOptionDao etudiantOptionDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private MyOptionService myOptionService;
}
