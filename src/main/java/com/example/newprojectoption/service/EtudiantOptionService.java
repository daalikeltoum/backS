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


    public EtudiantOption findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(String cne, String codeoption, Long anne, int semstreCode) {
        return etudiantOptionDao.findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(cne, codeoption, anne, semstreCode);
    }

    //save de etudiantOption
    public void saveEtudiantOption(String cetudiant, String cmyOption, Long annee, int csemestre) {

        Etudiant etudiant = etudiantService.findByCne(cetudiant);
        MyOption myOption = myOptionService.findByCode(cmyOption);
        Semestre semestre = semestreService.findByCode(csemestre);

        EtudiantOption etudiantOption = new EtudiantOption();
        etudiantOption.setEtudiant(etudiant);
        etudiantOption.setMyOption(myOption);
        etudiantOption.setAnnee(annee);
        etudiantOption.setSemestre(semestre);
        etudiantOptionDao.save(etudiantOption);
    }

    //save de etudiantModule

    public void saveEtudiantModule(String  cetudiant, int codesemestre1, int codeSemestre2, Long annee, String  cmyOption) {
        MyOption myOption = myOptionService.findByCode(cmyOption);
        Etudiant etudiant  = etudiantService.findByCne(cetudiant);
        List<ModuleSemestreOption> moduleSemestreOptions1 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codesemestre1, annee, myOption.getCode());
        List<ModuleSemestreOption> moduleSemestreOptions2 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codeSemestre2, annee+1, myOption.getCode());
        moduleSemestreOptions1.addAll(moduleSemestreOptions2);
        for (ModuleSemestreOption moduleSemestreOption : moduleSemestreOptions1) {
            InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
            inscriptionEtudiantModule.setEtudiant(etudiant);
            inscriptionEtudiantModule.setModuleSemestreOption(moduleSemestreOption);
            inscriptionEtudiantModule.setCode(etudiant.getCne() + moduleSemestreOption.getCode());
            inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
        }
    }

    //save etudiant reinscrit

    public void saveEtudiantReinscrit(int codeSemsetre1, int codeSemestre2, String cetudiant, String cmyOption, Long annee1) {
        Etudiant etudiant = etudiantService.findByCne(cetudiant);
        MyOption myOption = myOptionService.findByCode(cmyOption);
        //flparam khesna nhetou annee-1
        List<NoteEtudiantModule> noteEtudiantModules1 = noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemsetre1, annee1, etudiant.getCne());
        List<NoteEtudiantModule> noteEtudiantModules2 = noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemsetre1-1, annee1-1, etudiant.getCne());
        noteEtudiantModules1.addAll(noteEtudiantModules2);

        for (NoteEtudiantModule noteEtudiantModule : noteEtudiantModules1) {
            if (noteEtudiantModule.getEtatValidation().getCode().equals("NV")) {
                InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
                inscriptionEtudiantModule.setEtudiant(etudiant);
                //KHESNA NHETO LIH LMMODULE DYAL HAD L3AM
                Long anneeModule=noteEtudiantModule.getModuleSemestreOption().getAnneeUnvers();
                int codeSemestre=noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode();
                String codeModule=noteEtudiantModule.getModuleSemestreOption().getMyModule().getCode();
                ModuleSemestreOption nvModule=moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCodeAndMyModuleCode(codeSemestre,anneeModule+1,cmyOption,codeModule);
                inscriptionEtudiantModule.setModuleSemestreOption(nvModule);
                inscriptionEtudiantModule.setCode(etudiant.getCne() + noteEtudiantModule.getModuleSemestreOption().getCode());
                inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
                if (findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(etudiant.getCne(), myOption.getCode(), anneeModule+1,codeSemestre) == null) {
                    saveEtudiantOption(cetudiant, cmyOption, anneeModule+1,codeSemestre);
                }

            }
        }
    }

    public int save(EtudiantOption etudiantOption) {

        if (findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(etudiantOption.getEtudiant().getCne(), etudiantOption.getMyOption().getCode(), etudiantOption.getAnnee(), etudiantOption.getSemestre().getCode()) != null) {
            return -1;
        }
        Etudiant etudiant = etudiantService.findByCne(etudiantOption.getEtudiant().getCne());
        MyOption myOption = myOptionService.findByCode(etudiantOption.getMyOption().getCode());
        Semestre semestre = semestreService.findByCode(etudiantOption.getSemestre().getCode());
        Long annee=etudiantOption.getAnnee();
        int codeSemestre = etudiantOption.getSemestre().getCode();

        if (codeSemestre == 3 || codeSemestre == 5) {
            if(etudiant!=null) {
                    //chercher les notes de semestre avant
                    NoteEtudiantSemestre noteEtudiantSemestre1 = noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(etudiantOption.getEtudiant().getCne(), codeSemestre - 1);
                    NoteEtudiantSemestre noteEtudiantSemestre2 = noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(etudiantOption.getEtudiant().getCne(), codeSemestre - 2);
                    if (noteEtudiantSemestre1.getEtatValidation().getCode().equals("V") && noteEtudiantSemestre2.getEtatValidation().getCode().equals("V")) {
                        //premiere semsetre de l'annee suivant (s3,s5)
                        saveEtudiantOption(etudiant.getCne(), myOption.getCode(), annee, semestre.getCode());
                        //deuxieme semestre S4 S6
                        saveEtudiantOption(etudiant.getCne(), myOption.getCode(), annee+1, semestre.getCode() + 1);
                        //affecter modules:
                        saveEtudiantModule(etudiant.getCne(), codeSemestre, codeSemestre + 1, annee, myOption.getCode());
                    } else {
                        saveEtudiantReinscrit(codeSemestre - 1, codeSemestre - 2, etudiant.getCne(), myOption.getCode(), annee);
                    }
                   return 1;
                }else
                    return -1;
            }
            return -2;
        }


    public List<EtudiantOption> findByMyOptionCodeAndAnneeAndSemestreCode(String code, Long annee, int codeSems) {

        List<EtudiantOption> res=etudiantOptionDao.findByMyOptionCodeAndAnneeAndSemestreCode(code, annee, codeSems);
        if(res.size()!=0){
            return res;
        }
       else if(codeSems!=1)
           {
               List<EtudiantOption> etdudiants1=etudiantOptionDao.findByMyOptionCodeAndAnneeAndSemestreCode(code, annee-1, codeSems-2);
               List<EtudiantOption> etdudiants2=etudiantOptionDao.findByMyOptionCodeAndAnneeAndSemestreCode(code, annee, codeSems-1);
               etdudiants1.addAll(etdudiants2);
               for(EtudiantOption etudiantOption:etdudiants1){
                   EtudiantOption etudiantOption1=new EtudiantOption();
                   etudiantOption1.setSemestre(etudiantOption.getSemestre());
                   etudiantOption1.setAnnee(etudiantOption.getAnnee());
                   etudiantOption1.setEtudiant(etudiantOption.getEtudiant());
                   etudiantOption1.setMyOption(etudiantOption.getMyOption());
                   int result=this.save(etudiantOption1);
               }

           }

        return etudiantOptionDao.findByMyOptionCodeAndAnneeAndSemestreCode(code, annee, codeSems);

    }

    public int newEtudiant(EtudiantOption etudiantOption) {

        Etudiant etudiant = etudiantOption.getEtudiant();
        Etudiant etud=etudiantService.findByCne(etudiant.getCne());
        MyOption myOption = myOptionService.findByCode(etudiantOption.getMyOption().getCode());
        Semestre semestre = semestreService.findByCode(etudiantOption.getSemestre().getCode());
        Long annee=etudiantOption.getAnnee();

        if (etud == null) {
            //premiere semestre 2020
            etudiantService.save(etudiant);
            saveEtudiantOption(etudiant.getCne(), myOption.getCode(), annee,1);
            //deuxieme semstre 2021
            saveEtudiantOption(etudiant.getCne(), myOption.getCode(), annee+1, 2);
            //affecter modules
            saveEtudiantModule(etudiant.getCne(), 1, 2, annee, myOption.getCode());
            return 1;
        }else
            return -1;

    }




}


