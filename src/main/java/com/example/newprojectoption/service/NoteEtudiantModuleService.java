package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.MyModule;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import com.example.newprojectoption.dao.NoteEtudiantModuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteEtudiantModuleService {
    public List<MyModule> findByMyModuleCode(String code) {
        return noteEtudiantModuleDao.findByMyModuleCode(code);
    }

    public List<Etudiant> findByEtudiantCne(String cne) {
        return noteEtudiantModuleDao.findByEtudiantCne(cne);
    }

    public NoteEtudiantModule findByCode(String code) {
        return noteEtudiantModuleDao.findByCode(code);
    }
    public int save(NoteEtudiantModule noteEtudiantModule){
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
    }

    @Autowired
    private NoteEtudiantModuleDao noteEtudiantModuleDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private MyModuleService MyModuleService;
}
