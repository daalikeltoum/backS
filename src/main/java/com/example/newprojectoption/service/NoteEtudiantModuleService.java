package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.*;
import com.example.newprojectoption.dao.NoteEtudiantModuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NoteEtudiantModuleService {


    /*public int save(NoteEtudiantModule noteEtudiantModule){
        if(findByCode(noteEtudiantModule.getCode())!=null){
            return -1;
        }
        Etudiant etudiant=etudiantService.findByCne(noteEtudiantModule.getEtudiant().getCne());
        MyModule myModule=MyModuleService.findByCode(noteEtudiantModule.getMyModule().getCode());
        if(etudiant==null || myModule==null){
            return -2;
        }
        else{
            noteEtudiantModule.setEtudiant(etudiant);
            noteEtudiantModule.setMyModule(myModule);
            noteEtudiantModuleDao.save(noteEtudiantModule);
            return 1;
        }
    }*/


    @Autowired
    private NoteEtudiantModuleDao noteEtudiantModuleDao;
    @Autowired
    private ModuleSemestreOptionService moduleSemestreOptionService;
    @Autowired
    private EtudiantOptionService etudiantOptionService;
    @Autowired
    private EtatValidationService etatValidationService;
    @Autowired
    private NoteEtudiantSemestreService noteEtudiantSemestreService;
    private NoteEtudiantModule noteEtudiantModule;
    public List<NoteEtudiantModule> findAll() {
        return noteEtudiantModuleDao.findAll();
    }

    public List<NoteEtudiantModule> findByEtudiantCne(String cne) {
        return noteEtudiantModuleDao.findByEtudiantCne(cne);
    }

    public List<NoteEtudiantModule> findNotes(String codeModule, String codeOption) {
        List<NoteEtudiantModule> res=noteEtudiantModuleDao.findByModuleSemestreOptionCode(codeModule);
        if(res.size()!=0){
            /*NoteEtudiantSemestre notSemestre = noteEtudiantSemestreService.findByCode(noteEtudiantModule.getNoteEtudiantSemestre().getCode());*/

            return res;
        }
        else{
            List<EtudiantOption> etudiants=etudiantOptionService.findByMyOptionCode(codeOption);
            for (EtudiantOption etudiantOption : etudiants) {
                NoteEtudiantModule noteEtudiantModule=new NoteEtudiantModule();

                /*NoteEtudiantSemestre noteEtudiantSemestre= new NoteEtudiantSemestre();
                noteEtudiantSemestre.setEtudiant(noteEtudiantModule.getEtudiant());
                noteEtudiantSemestre.setSemestre(noteEtudiantModule.getModuleSemestreOption().getSemestre());
                noteEtudiantSemestre.setNote(BigDecimal.ZERO);*/

                noteEtudiantModule.setEtudiant(etudiantOption.getEtudiant());
                noteEtudiantModule.setNoteModuleNormal(BigDecimal.ZERO);
                noteEtudiantModule.setNoteModuleRat(BigDecimal.ZERO);
                noteEtudiantModule.setNoteGlobale(BigDecimal.ZERO);
                noteEtudiantModule.setNoteContinue(BigDecimal.ZERO);
                noteEtudiantModule.setNoteFinalApresRat(BigDecimal.ZERO);
                noteEtudiantModule.setNoteFinalAvRat(BigDecimal.ZERO);
                noteEtudiantModule.setEtatValidation(etatValidationService.findByCode("R"));
                noteEtudiantModule.setModuleSemestreOption(moduleSemestreOptionService.findByCode(codeModule));
                noteEtudiantModuleDao.save(noteEtudiantModule);
                res.add(noteEtudiantModule);

            }
            return res;
        }
        //List<NoteEtudiantSemestre> notSemestre= noteEtudiantModul
    }

    public void update(NoteEtudiantModule noteEtudiantModule){
        EtatValidation etatValidation=etatValidationService.findByLibelle(noteEtudiantModule.getEtatValidation().getLibelle());
        noteEtudiantModule.setEtatValidation(etatValidation);
        noteEtudiantModuleDao.save(noteEtudiantModule);
    }
    public List<NoteEtudiantModule> findByModuleSemestreOptionMyOptionCodeAndModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnvers(String code1, String code2, String anneeUnvers) {
        return noteEtudiantModuleDao.findByModuleSemestreOptionMyOptionCodeAndModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnvers(code1, code2, anneeUnvers);
    }
}
