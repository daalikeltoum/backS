package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.bean.ModuleSemestreOption;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import com.example.newprojectoption.bean.NoteEtudiantSemestre;
import com.example.newprojectoption.dao.NoteEtudiantSemestreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;


@Service
public class NoteEtudiantSemestreService {

    public List<NoteEtudiantSemestre> findAll() {
        return noteEtudiantSemestreDao.findAll();
    }

    public NoteEtudiantSemestre findByEtudiantCneAndSemestreCode(String cne, int code) {
        return noteEtudiantSemestreDao.findByEtudiantCneAndSemestreCode(cne, code);
    }

    public List<NoteEtudiantSemestre> findBySemestreCode(String code) {
        return noteEtudiantSemestreDao.findBySemestreCode(code);
    }

    public List<NoteEtudiantSemestre> findByEtudiantCne(String cne) {
        return noteEtudiantSemestreDao.findByEtudiantCne(cne);
    }
    /*public void saveNoteSemestre(String optionCode,String semetreCode,String annee){
    List <EtudiantOption> etudiantOptions=etudiantOptionService.findByMyOptionCodeAndAnnee(optionCode,annee);
    for(EtudiantOption etud:etudiantOptions){
        List<NoteEtudiantModule> notesModules=noteEtudiantModuleService.findByEtudiantCne(etud.getEtudiant().getCne());
        NoteEtudiantSemestre noteEtudiantSemestre=new NoteEtudiantSemestre();
        noteEtudiantSemestre.setEtudiant(etud.getEtudiant());
        noteEtudiantSemestre.setSemestre(semestreService.findByCode(semetreCode));
        BigDecimal noteSem=new BigDecimal(0);
        for(NoteEtudiantModule noteModule:notesModules){
            noteSem.add(noteModule.getNoteGlobale());
        }
        noteEtudiantSemestre.setNoteSemestre(noteSem);

        noteEtudiantSemestreDao.save(noteEtudiantSemestre);
    }


    }*/
    public void save(NoteEtudiantSemestre noteEtudiantSemestre){
        noteEtudiantSemestreDao.save(noteEtudiantSemestre);
    }

    @Autowired
    private NoteEtudiantSemestreDao noteEtudiantSemestreDao;
    @Autowired
    private SemestreService semestreService;
    @Autowired
    private EtudiantOptionService etudiantOptionService;
    @Autowired
    private NoteEtudiantModuleService noteEtudiantModuleService;
    @Autowired
    private EtatValidationService etatValidationService;
    @Autowired
    private ModuleSemestreOptionService moduleSemestreOptionService;

    public NoteEtudiantSemestre findByCode(String code) {
        return noteEtudiantSemestreDao.findByCode(code);
    }

    public List<NoteEtudiantSemestre> notesSemestre(int codeSemestre,String codeOption,String libelle){

        List<NoteEtudiantSemestre> notesSemestre=new ArrayList<NoteEtudiantSemestre>();
        List<EtudiantOption> etudiants=etudiantOptionService.findByMyOptionCodeAndAnneeUniversitaireLibelleAndSemestreCode(codeOption,libelle,codeSemestre);

        for (EtudiantOption etudiantOption : etudiants) {
            BigDecimal som = new BigDecimal(0);
            int i = 0;
            NoteEtudiantSemestre noteEtudiantSemestre = noteEtudiantSemestreDao.findByCode(etudiantOption.getEtudiant().getCne() + codeSemestre);

            List<ModuleSemestreOption> myModules = moduleSemestreOptionService.findBySemestreCodeAndMyOptionCode(codeSemestre, codeOption);
            for (ModuleSemestreOption moduleSemestreOption : myModules) {
                List<NoteEtudiantModule> notesModules = noteEtudiantModuleService.findByModuleSemestreOptionMyModuleCodeAndEtudiantCne(moduleSemestreOption.getMyModule().getCode(),etudiantOption.getEtudiant().getCne());
                NoteEtudiantModule notetudiantModule;
                if (notesModules.size() == 1) {
                    notetudiantModule = notesModules.get(0);

                } else {
                    if (notesModules.get(0).getModuleSemestreOption().getAnneeUniversitaire().getAnneeOne() > notesModules.get(1).getModuleSemestreOption().getAnneeUniversitaire().getAnneeOne())
                        notetudiantModule = notesModules.get(0);
                    else
                        notetudiantModule = notesModules.get(1);
                }
                if (notetudiantModule.getModuleSemestreOption().getTypeModule().getCode().equals("majeur") && notetudiantModule.getNoteGlobale().compareTo(new BigDecimal(8)) == -1) {
                    i = i + 1;
                }
                else if (notetudiantModule.getModuleSemestreOption().getTypeModule().getCode().equals("complementaire") && notetudiantModule.getNoteGlobale().compareTo(new BigDecimal(6)) == -1) {
                    i = i + 1;
                }
                som = som.add(notetudiantModule.getNoteGlobale());
            }
            BigDecimal length=new BigDecimal(myModules.size());
            BigDecimal note=som.divide(length, 3, RoundingMode.HALF_EVEN);
            if(note.compareTo(new BigDecimal(10))==-1 || i!=0){
                noteEtudiantSemestre.setEtatValidation(etatValidationService.findByCode("NV"));
            }else{
                noteEtudiantSemestre.setEtatValidation(etatValidationService.findByCode("V"));
            }
            noteEtudiantSemestre.setNoteSemestre(note);
            noteEtudiantSemestreDao.save(noteEtudiantSemestre);
            notesSemestre.add(noteEtudiantSemestre);
        }
        return notesSemestre;
    }


}
