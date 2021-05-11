package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import com.example.newprojectoption.bean.NoteEtudiantSemestre;
import com.example.newprojectoption.dao.NoteEtudiantModuleDao;
import com.example.newprojectoption.dao.NoteEtudiantSemestreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class NoteEtudiantSemestreService {

    public List<NoteEtudiantSemestre> findAll() {
        return noteEtudiantSemestreDao.findAll();
    }

    public NoteEtudiantSemestre findByEtudiantCneAndSemestreCode(String cne, String code) {
        return noteEtudiantSemestreDao.findByEtudiantCneAndSemestreCode(cne, code);
    }

    public List<NoteEtudiantSemestre> findBySemestreCode(String code) {
        return noteEtudiantSemestreDao.findBySemestreCode(code);
    }

    public List<NoteEtudiantSemestre> findByEtudiantCne(String cne) {
        return noteEtudiantSemestreDao.findByEtudiantCne(cne);
    }
    public void saveNoteSemestre(String optionCode,String semetreCode,String annee){
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
        noteEtudiantSemestre.setNote(noteSem);

        noteEtudiantSemestreDao.save(noteEtudiantSemestre);
    }


    }

    @Autowired
    private NoteEtudiantSemestreDao noteEtudiantSemestreDao;
    @Autowired
    private SemestreService semestreService;
    @Autowired
    private EtudiantOptionService etudiantOptionService;
    @Autowired
    private NoteEtudiantModuleService noteEtudiantModuleService;

    public NoteEtudiantSemestre findByCode(String code) {
        return noteEtudiantSemestreDao.findByCode(code);
    }
}
