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

    //save de etudiantOption
    public void saveEtudiantOption(String cetudiant, String cmyOption, String annee, int csemestre) {

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

    public void saveEtudiantModule(String  cetudiant, int codesemestre1, int codeSemestre2, String annee, String  cmyOption) {
        MyOption myOption = myOptionService.findByCode(cmyOption);
        Etudiant etudiant  = etudiantService.findByCne(cetudiant);
        List<ModuleSemestreOption> moduleSemestreOptions1 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codesemestre1, annee, myOption.getCode());
        List<ModuleSemestreOption> moduleSemestreOptions2 = moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codeSemestre2, annee, myOption.getCode());
        moduleSemestreOptions1.addAll(moduleSemestreOptions2);
        for (ModuleSemestreOption moduleSemestreOption : moduleSemestreOptions1) {
            InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
            inscriptionEtudiantModule.setEtudiant(etudiant);
            inscriptionEtudiantModule.setModuleSemestreOption(moduleSemestreOption);
            inscriptionEtudiantModule.setCode(etudiant.getCne() + moduleSemestreOption.getCode() + annee);
            inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
        }
    }

    //save etudiant reinscrit

    public void saveEtudiantReinscrit(int codeSemsetre1, int codeSemestre2, String cetudiant, String cmyOption, String annee1,String annee2) {
        Etudiant etudiant = etudiantService.findByCne(cetudiant);
        MyOption myOption = myOptionService.findByCode(cmyOption);
        //flparam khesna nhetou annee-1
        List<NoteEtudiantModule> noteEtudiantModules1 = noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemsetre1, annee2, etudiant.getCne());
        List<NoteEtudiantModule> noteEtudiantModules2 = noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemestre2, annee2, etudiant.getCne());
        noteEtudiantModules1.addAll(noteEtudiantModules2);

        for (NoteEtudiantModule noteEtudiantModule : noteEtudiantModules1) {
            if (noteEtudiantModule.getEtatValidation().getCode() == "NV") {
                InscriptionEtudiantModule inscriptionEtudiantModule = new InscriptionEtudiantModule();
                inscriptionEtudiantModule.setEtudiant(etudiant);
                //KHESNA NHETO LIH LMMODULE SYAL HAD L3AM
                ModuleSemestreOption nvModule=moduleSemestreOptionService.findByCode(cmyOption+noteEtudiantModule.getModuleSemestreOption().getMyModule().getCode()+noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode()+annee1);
                inscriptionEtudiantModule.setModuleSemestreOption(nvModule);
                inscriptionEtudiantModule.setCode(etudiant.getCne() + noteEtudiantModule.getModuleSemestreOption().getCode() + annee1);
                inscriptionEtudiantModuleService.save(inscriptionEtudiantModule);
                if (findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(etudiant.getCne(), myOption.getCode(), annee1, noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode()) == null) {
                    saveEtudiantOption(cetudiant, cmyOption, annee1, noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode());
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
        String annee=etudiantOption.getAnnee();
        int codeSemestre = etudiantOption.getSemestre().getCode();

        if (codeSemestre == 1 || codeSemestre == 3 || codeSemestre == 5) {
            if (etudiant == null) {
                //premiere semestre
                etudiantService.save(etudiantOption.getEtudiant());
                saveEtudiantOption(etudiantOption.getEtudiant().getCne(), myOption.getCode(), annee, semestre.getCode());
                //deuxieme semstre
                saveEtudiantOption(etudiantOption.getEtudiant().getCne(), myOption.getCode(), annee, etudiantOption.getSemestre().getCode() + 1);
               //affecter modules
                saveEtudiantModule(etudiantOption.getEtudiant().getCne(), 1, 2, annee, myOption.getCode());

            } else {
                if (codeSemestre == 1) {
                    saveEtudiantReinscrit(1, 2, etudiant.getCne(), myOption.getCode(),annee,annee);
                } else {
                    //chercher les notes de semestre avant
                    NoteEtudiantSemestre noteEtudiantSemestre1 = noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(etudiantOption.getEtudiant().getCne(), codeSemestre - 1);
                    NoteEtudiantSemestre noteEtudiantSemestre2 = noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(etudiantOption.getEtudiant().getCne(), codeSemestre - 2);
                    if (noteEtudiantSemestre1.getEtatValidation().getCode() == "V" && noteEtudiantSemestre2.getEtatValidation().getCode() == "V") {
                        //premiere semsetre de l'annee suivant (s3,s5)
                        saveEtudiantOption(etudiant.getCne(), myOption.getCode(), annee, semestre.getCode());
                        //deuxieme semestre
                        saveEtudiantOption(etudiant.getCne(), myOption.getCode(), annee, semestre.getCode() + 1);
                        //affecter modules:
                        saveEtudiantModule(etudiant.getCne(), codeSemestre, codeSemestre + 1, annee, myOption.getCode());
                    } else {
                        saveEtudiantReinscrit(codeSemestre - 1, codeSemestre - 2, etudiant.getCne(), myOption.getCode(), annee, annee);
                    }
                }
            }
        }
        return 1;
    }
}
