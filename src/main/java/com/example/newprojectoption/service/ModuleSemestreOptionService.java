package com.example.newprojectoption.service;


import com.example.newprojectoption.bean.*;
import com.example.newprojectoption.dao.ModuleSemestreOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuleSemestreOptionService {
    @Autowired
    private ModuleSemestreOptionDao moduleSemestreOptionDao;
    @Autowired
    private MyModuleService myModuleService;
    @Autowired
    private MyOptionService myOptionService;
    @Autowired
    private SemestreService semestreService;
    @Autowired
    private TypeModuleService typeModuleService;

    public List<ModuleSemestreOption> findBySemestreCodeAndMyOptionCode(int codeSem, String codeOpt) {
        return moduleSemestreOptionDao.findBySemestreCodeAndMyOptionCode(codeSem, codeOpt);
    }

    public List<ModuleSemestreOption> findByMyOptionCode(String code) {
        return moduleSemestreOptionDao.findByMyOptionCode(code);
    }

    public int  save (ModuleSemestreOption moduleSemestreOption){
        if(findByCode(moduleSemestreOption.getCode())!=null)
            return -1;
        int codeSemes=moduleSemestreOption.getSemestre().getCode();
        String codeType=moduleSemestreOption.getTypeModule().getCode();
        String codeOpt=moduleSemestreOption.getMyOption().getCode();
        String codeModule=moduleSemestreOption.getMyModule().getCode();

        Semestre semestre=semestreService.findByCode(codeSemes);
        MyModule myModule=myModuleService.findByCode(codeModule);
        MyOption myOption=myOptionService.findByCode(codeOpt);
        TypeModule typeModule=typeModuleService.findByCode(codeType);

        if(semestre==null || typeModule==null || myModule==null || myOption==null)
            return -2;
        moduleSemestreOption.setSemestre(semestre);
        moduleSemestreOption.setMyModule(myModule);
        moduleSemestreOption.setMyOption(myOption);
        moduleSemestreOption.setTypeModule(typeModule);
        moduleSemestreOptionDao.save(moduleSemestreOption);
            return 1;
    }

    public ModuleSemestreOption findByMyOptionCodeAndMyModuleCodeAndSemestreCode(String codeOp, String codeMod, String codeSeme) {
        return moduleSemestreOptionDao.findByMyOptionCodeAndMyModuleCodeAndSemestreCode(codeOp, codeMod, codeSeme);
    }



    public List<ModuleSemestreOption> findAll() {
        return moduleSemestreOptionDao.findAll();
    }

    public ModuleSemestreOption findByCode(String code) {
        return moduleSemestreOptionDao.findByCode(code);
    }

    public ModuleSemestreOption findBySemestreCodeAndAnneeUniversitaireAnneeOneAndMyOptionCodeAndMyModuleCode(int codeSemestre, Long annee, String cmyOption, String codeModule) {
        return moduleSemestreOptionDao.findBySemestreCodeAndAnneeUniversitaireAnneeOneAndMyOptionCodeAndMyModuleCode(codeSemestre, annee, cmyOption, codeModule);
    }

    public List<ModuleSemestreOption> findBySemestreCodeAndAnneeUniversitaireLibelleAndMyOptionCode(int codeSemestre, String annee, String cmyOption) {
        return moduleSemestreOptionDao.findBySemestreCodeAndAnneeUniversitaireLibelleAndMyOptionCode(codeSemestre, annee, cmyOption);
    }
    @Transactional
    public int deleteByCode(String code) {
        return moduleSemestreOptionDao.deleteByCode(code);
    }

    @Transactional
    public int deleteByMyOptionCode(String code) {
        return moduleSemestreOptionDao.deleteByMyOptionCode(code);
    }

    @Transactional
    public int deleteByMyModuleCode(String code) {
        return moduleSemestreOptionDao.deleteByMyModuleCode(code);
    }
}
