package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.*;
import com.example.newprojectoption.dao.NoteEtudiantModuleDao;
import com.example.newprojectoption.dao.NoteEtudiantSemestreDao;
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
    private SemestreService semestreService;
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
            return res;
        }
        else{
            List<EtudiantOption> etudiants=etudiantOptionService.findByMyOptionCode(codeOption);

            for (EtudiantOption etudiantOption : etudiants) {
                NoteEtudiantModule noteEtudiantModule=new NoteEtudiantModule();

                noteEtudiantModule.setEtudiant(etudiantOption.getEtudiant());
                noteEtudiantModule.setNoteModuleNormal(BigDecimal.ZERO);
                noteEtudiantModule.setNoteModuleRat(BigDecimal.ZERO);
                noteEtudiantModule.setNoteGlobale(BigDecimal.ZERO);
                noteEtudiantModule.setNoteContinue(BigDecimal.ZERO);
                noteEtudiantModule.setNoteFinalApresRat(BigDecimal.ZERO);
                noteEtudiantModule.setNoteFinalAvRat(BigDecimal.ZERO);
                noteEtudiantModule.setEtatValidation(etatValidationService.findByCode("R"));
                noteEtudiantModule.setModuleSemestreOption(moduleSemestreOptionService.findByCode(codeModule));

                NoteEtudiantSemestre noteEtudiantSemestre= noteEtudiantSemestreService.findByCode(etudiantOption.getEtudiant().getCne()+noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode());

                if(noteEtudiantSemestre==null){
                    noteEtudiantSemestre=new NoteEtudiantSemestre();
                    noteEtudiantSemestre.setEtudiant(etudiantOption.getEtudiant());
                    noteEtudiantSemestre.setNoteSemestre(BigDecimal.ZERO);
                    noteEtudiantSemestre.setCode(etudiantOption.getEtudiant().getCne()+noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode());
                    noteEtudiantSemestre.setSemestre(semestreService.findByCode(noteEtudiantModule.getModuleSemestreOption().getSemestre().getCode()));
                    noteEtudiantSemestreService.save(noteEtudiantSemestre);
                }

                noteEtudiantModule.setNoteEtudiantSemestre(noteEtudiantSemestre);
                noteEtudiantModuleDao.save(noteEtudiantModule);
                res.add(noteEtudiantModule);

            }
            return res;
        }
    }

    public void update(NoteEtudiantModule noteEtudiantModule){
        NoteEtudiantSemestre noteEtudiantSemestre= noteEtudiantSemestreService.findByCode(noteEtudiantModule.getNoteEtudiantSemestre().getCode());
        EtatValidation etatValidation=etatValidationService.findByLibelle(noteEtudiantModule.getEtatValidation().getLibelle());
        noteEtudiantModule.setEtatValidation(etatValidation);
       /* noteEtudiantSemestre.getNoteSemestre().add(new BigDecimal(20));
        noteEtudiantSemestreService.save(noteEtudiantSemestre);*/
        noteEtudiantModuleDao.save(noteEtudiantModule);
    }

    public List<NoteEtudiantModule> findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(int codeSemestre,Long annee, String cne) {
        return noteEtudiantModuleDao.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemestre,annee, cne);
    }

    public List<NoteEtudiantModule> findByEtatValidationCode(String code) {
        return noteEtudiantModuleDao.findByEtatValidationCode(code);
    }

    public List<NoteEtudiantModule> findByModuleSemestreOptionCodeAndEtatValidationCode(String code, String codeEtat) {
        return noteEtudiantModuleDao.findByModuleSemestreOptionCodeAndEtatValidationCode(code, codeEtat);
    }
}
