package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.*;
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



    public List<EtudiantOption> findByMyOptionCodeAndAnnee(String code, String annee) {
        return etudiantOptionDao.findByMyOptionCodeAndAnnee(code, annee);
    }

    public EtudiantOption findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(String cne, String codeoption, String anne, String semstreCode) {
        return etudiantOptionDao.findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(cne, codeoption, anne, semstreCode);
    }

    public int save(EtudiantOption etudiantOption){
      if(findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(etudiantOption.getEtudiant().getCne(),etudiantOption.getMyOption().getCode(),etudiantOption.getAnnee(),etudiantOption.getSemestre().getCode())!=null){
          return -1;
      }
        Etudiant etudiant=etudiantService.findByCne(etudiantOption.getEtudiant().getCne());
        MyOption myOption=myOptionService.findByCode(etudiantOption.getMyOption().getCode());
        if(etudiant!=null){
            etudiantOption.setEtudiant(etudiant);
            etudiantOption.setMyOption(myOption);
            etudiantOption.setAnnee(etudiantOption.getAnnee());
            etudiantOption.setSemestre(semestreService.findByCode(etudiantOption.getSemestre().getCode()));
            etudiantOptionDao.save(etudiantOption);
        }
        else {
            etudiantService.save(etudiant);
            etudiantOption.setEtudiant(etudiant);
            etudiantOption.setMyOption(myOption);
            etudiantOption.setAnnee(etudiantOption.getAnnee());
            etudiantOption.setSemestre(etudiantOption.getSemestre());
            etudiantOptionDao.save(etudiantOption);

        }

        List<ModuleSemestreOption> moduleSemestreOptions=moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(etudiantOption.getSemestre().getCode(),etudiantOption.getAnnee(),etudiantOption.getMyOption().getCode());
        for (ModuleSemestreOption moduleSemestreOption:moduleSemestreOptions){
            InscriptionEtudiantModule inscriptionEtudiantModule=new InscriptionEtudiantModule();
            inscriptionEtudiantModule.setEtudiant(etudiant);
            inscriptionEtudiantModule.setModuleSemestreOption(moduleSemestreOption);
            inscriptionEtudiantModule.setCode(etudiant.getCne()+moduleSemestreOption.getCode()+etudiantOption.getAnnee());
            inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
        }
        return 1;
    }


    @Autowired
    private EtudiantOptionDao etudiantOptionDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private SemestreService semestreService;
    @Autowired
    private MyOptionService myOptionService;
    @Autowired
    private InscriptionEtudiantModuleService inscriptionEtudiantModuleService;
    @Autowired
    private ModuleSemestreOptionService moduleSemestreOptionService;


}
