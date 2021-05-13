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
    @Autowired
    private NoteEtudiantSemestreService noteEtudiantSemestreService;
    @Autowired
    private NoteEtudiantModuleService noteEtudiantModuleService;

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

    public EtudiantOption findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(String cne, String codeoption, String anne, int semstreCode) {
        return etudiantOptionDao.findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(cne, codeoption, anne, semstreCode);
    }

    public int save(EtudiantOption etudiantOption) {
        if (findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(etudiantOption.getEtudiant().getCne(), etudiantOption.getMyOption().getCode(), etudiantOption.getAnnee(), etudiantOption.getSemestre().getCode()) != null) {
            return -1;
        }
        Etudiant etudiant = etudiantService.findByCne(etudiantOption.getEtudiant().getCne());
        MyOption myOption = myOptionService.findByCode(etudiantOption.getMyOption().getCode());
        int codeSemestre = etudiantOption.getSemestre().getCode();
        int codeSemsetreav1 = codeSemestre - 1;
        int codeSemsetreav2 = codeSemestre - 2;
        Semestre semeav1 = semestreService.findByCode(codeSemsetreav1);
        Semestre semeav2 = semestreService.findByCode(codeSemsetreav2);
        if (codeSemestre == 1 || codeSemestre == 3 || codeSemestre == 5) {
            if (etudiant == null) {
                etudiantService.save(etudiantOption.getEtudiant());
                etudiantOption.setEtudiant(etudiantOption.getEtudiant());
                etudiantOption.setMyOption(myOption);
                etudiantOption.setAnnee(etudiantOption.getAnnee());
                etudiantOption.setSemestre(etudiantOption.getSemestre());
                etudiantOptionDao.save(etudiantOption);

                List<ModuleSemestreOption> moduleSemestreOptions1 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(1, etudiantOption.getAnnee(), etudiantOption.getMyOption().getCode());
                List<ModuleSemestreOption> moduleSemestreOptions2 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(2, etudiantOption.getAnnee(), etudiantOption.getMyOption().getCode());
                moduleSemestreOptions1.addAll(moduleSemestreOptions2);

                for (ModuleSemestreOption moduleSemestreOption : moduleSemestreOptions1) {
                    InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
                    inscriptionEtudiantModule.setEtudiant(etudiantOption.getEtudiant());
                    inscriptionEtudiantModule.setModuleSemestreOption(moduleSemestreOption);
                    inscriptionEtudiantModule.setCode(etudiantOption.getEtudiant().getCne() + moduleSemestreOption.getCode() + etudiantOption.getAnnee());
                    inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
                }
            } else {
                if (codeSemestre == 1){
                    List<NoteEtudiantModule> noteEtudiantModules1=noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(1,etudiantOption.getAnnee(),etudiantOption.getEtudiant().getCne());
                    List<NoteEtudiantModule> noteEtudiantModules2=noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(2,etudiantOption.getAnnee(),etudiantOption.getEtudiant().getCne());
                    noteEtudiantModules1.addAll(noteEtudiantModules2);
                    for(NoteEtudiantModule noteEtudiantModule:noteEtudiantModules1){
                        if(noteEtudiantModule.getEtatValidation().getCode()=="NV"){
                            InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
                            inscriptionEtudiantModule.setEtudiant(etudiantOption.getEtudiant());
                            inscriptionEtudiantModule.setModuleSemestreOption(noteEtudiantModule.getModuleSemestreOption());
                            inscriptionEtudiantModule.setCode(etudiantOption.getEtudiant().getCne() +noteEtudiantModule.getModuleSemestreOption().getCode() + etudiantOption.getAnnee());
                            inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);

                        }
                    }
                }else{

                List<NoteEtudiantModule> noteEtudiantModules1=noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemestre-1,etudiantOption.getAnnee(),etudiantOption.getEtudiant().getCne());
                List<NoteEtudiantModule> noteEtudiantModules2=noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemestre-2,etudiantOption.getAnnee(),etudiantOption.getEtudiant().getCne());
                noteEtudiantModules1.addAll(noteEtudiantModules2);

                NoteEtudiantSemestre noteEtudiantSemestre1=noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(etudiantOption.getEtudiant().getCne(),codeSemestre-1);
                NoteEtudiantSemestre noteEtudiantSemestre2=noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(etudiantOption.getEtudiant().getCne(),codeSemestre-2);
                if(noteEtudiantSemestre1.getEtatValidation().getCode()=="V" && noteEtudiantSemestre2.getEtatValidation().getCode()=="V"){
                    etudiantOption.setEtudiant(etudiantOption.getEtudiant());
                    etudiantOption.setMyOption(myOption);
                    etudiantOption.setAnnee(etudiantOption.getAnnee());
                    etudiantOption.setSemestre(semestreService.findByCode(etudiantOption.getSemestre().getCode()));
                    etudiantOptionDao.save(etudiantOption);
                    //affecter modules:
                    List<ModuleSemestreOption> moduleSemestreOptions1 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codeSemestre+1, etudiantOption.getAnnee(), etudiantOption.getMyOption().getCode());
                    List<ModuleSemestreOption> moduleSemestreOptions2 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codeSemestre+2, etudiantOption.getAnnee(), etudiantOption.getMyOption().getCode());
                    moduleSemestreOptions1.addAll(moduleSemestreOptions2);

                    for (ModuleSemestreOption moduleSemestreOption : moduleSemestreOptions1) {
                        InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
                        inscriptionEtudiantModule.setEtudiant(etudiantOption.getEtudiant());
                        inscriptionEtudiantModule.setModuleSemestreOption(moduleSemestreOption);
                        inscriptionEtudiantModule.setCode(etudiantOption.getEtudiant().getCne() + moduleSemestreOption.getCode() + etudiantOption.getAnnee());
                        inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
                    }
                }else{

                   for(NoteEtudiantModule noteEtudiantModule:noteEtudiantModules1){
                    if(noteEtudiantModule.getEtatValidation().getCode()=="NV"){
                        InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
                        inscriptionEtudiantModule.setEtudiant(etudiantOption.getEtudiant());
                        inscriptionEtudiantModule.setModuleSemestreOption(noteEtudiantModule.getModuleSemestreOption());
                        inscriptionEtudiantModule.setCode(etudiantOption.getEtudiant().getCne() +noteEtudiantModule.getModuleSemestreOption().getCode() + etudiantOption.getAnnee());
                        inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
                        etudiantOption.setEtudiant(etudiantOption.getEtudiant());
                        etudiantOption.setMyOption(myOption);
                        etudiantOption.setAnnee(etudiantOption.getAnnee());
                        etudiantOption.setSemestre(semestreService.findByCode(noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode()));
                        etudiantOptionDao.save(etudiantOption);

                    }
                }
                }
            }


        }

    }
        return 1;

}

}
