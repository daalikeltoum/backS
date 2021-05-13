package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import com.example.newprojectoption.bean.NoteEtudiantSemestre;
import com.example.newprojectoption.dao.NoteEtudiantModuleDao;
import com.example.newprojectoption.dao.NoteEtudiantSemestreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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

    public NoteEtudiantSemestre findByCode(String code) {
        return noteEtudiantSemestreDao.findByCode(code);
    }

    public List<NoteEtudiantSemestre> notesSemestre(int codeSemestre,String codeOption,String annee){

        List<NoteEtudiantSemestre> notesSemestre=new ArrayList<NoteEtudiantSemestre>();
        List<EtudiantOption> etudiants=etudiantOptionService.findByMyOptionCodeAndAnnee(codeOption,annee);

        for (EtudiantOption etudiantOption : etudiants) {
            BigDecimal som=new BigDecimal(0);
            NoteEtudiantSemestre noteEtudiantSemestre=noteEtudiantSemestreDao.findByCode(etudiantOption.getEtudiant().getCne()+codeSemestre);
           List<NoteEtudiantModule> notesModules= noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemestre,annee,etudiantOption.getEtudiant().getCne());
            for (NoteEtudiantModule notetudiantModule:notesModules){
               som=som.add(notetudiantModule.getNoteGlobale());

            }
            BigDecimal length=new BigDecimal(notesModules.size());
            noteEtudiantSemestre.setNoteSemestre(som.divide(length));
            noteEtudiantSemestreDao.save(noteEtudiantSemestre);
            notesSemestre.add(noteEtudiantSemestre);
        }
        return notesSemestre;
    }


}
