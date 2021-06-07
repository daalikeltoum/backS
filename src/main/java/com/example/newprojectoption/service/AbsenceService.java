package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.*;
import com.example.newprojectoption.dao.AbsenceDao;
import com.example.newprojectoption.dao.EtatValidationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbsenceService {
    @Autowired
    private AbsenceDao absenceDao;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private SeanceService seanceService;
    @Autowired
    private ImageService imageService;

    public int save(List<Absence> absences){
        for(Absence absence:absences){
            if(findByEtudiantCneAndSeanceLibelle(absence.getEtudiant().getCne(),absence.getSeance().getLibelle())!=null){
                return -1;
            }
            Absence absence1=new Absence();
            Etudiant etudiant =etudiantService.findByCne(absence.getEtudiant().getCne());
            Seance seance =seanceService.findByLibelle(absence.getSeance().getLibelle());
            if(etudiant==null || seance==null)
                return -2;
            absence1.setEtudiant(etudiant);
            absence1.setSeance(seance);
            absence1.setEtatAbsence(false);//justifi ou pas
            absence1.setEtatJustification("Aucune justification donnee");//accepte ou pas
            absenceDao.save(absence1);
        }
        return 1;
    }

    public Absence findByEtudiantCneAndSeanceLibelle(String cne, String libelle) {
        return absenceDao.findByEtudiantCneAndSeanceLibelle(cne, libelle);
    }

    public List<Absence> findByEtudiantCneAndSeanceModuleSemestreOptionSemestreCodeAndSeanceModuleSemestreOptionAnneeUniversitaireLibelle(String cne, int semestre, String anne) {
        return absenceDao.findByEtudiantCneAndSeanceModuleSemestreOptionSemestreCodeAndSeanceModuleSemestreOptionAnneeUniversitaireLibelle(cne, semestre, anne);
    }

    public List<Absence> findEtudiantAbsente(String cne, String codeModule) {
        return absenceDao.findEtudiantAbsente(cne, codeModule);
    }

    public int update (Absence absence){
        if(absence.getSeance()==null)
            return -7;
        Absence abs=findByEtudiantCneAndSeanceLibelle(absence.getEtudiant().getCne(),absence.getSeance().getLibelle());
        ImageModl image=imageService.findByNameData(absence.getImageModel().getNameData());
        if(abs==null || image==null)
            return -1;
        abs.setImageModel(image);
        abs.setEtatJustification(absence.getEtatJustification());
        abs.setEtatAbsence(absence.getEtatAbsence());
        absenceDao.save(abs);
        return 1;
    }
}
