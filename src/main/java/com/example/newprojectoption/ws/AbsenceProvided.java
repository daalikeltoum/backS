package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.Absence;
import com.example.newprojectoption.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ispits-project/absence")

public class AbsenceProvided {
    @Autowired
    private AbsenceService absenceService;

    @PostMapping("/")
    public int save(@RequestBody  List<Absence> absences) {
        return absenceService.save(absences);
    }


    @GetMapping("/etudiant/cne/{cne}/seance/moduleSemestreOption/semestre/code/{semestre}/seance/moduleSemestreOption/anneuniv/libelle/{anne}")
    public List<Absence> findByEtudiantCneAndSeanceModuleSemestreOptionSemestreCodeAndSeanceModuleSemestreOptionAnneeUniversitaireLibelle(@PathVariable String cne,@PathVariable int semestre,@PathVariable String anne) {
        return absenceService.findByEtudiantCneAndSeanceModuleSemestreOptionSemestreCodeAndSeanceModuleSemestreOptionAnneeUniversitaireLibelle(cne, semestre, anne);
    }
    @GetMapping("/etudiant/cne/{cne}/seance/moduleSemestreOption/code/{codeModule}")
    public List<Absence> findEtudiantAbsente(@PathVariable String cne,@PathVariable String codeModule) {
        return absenceService.findEtudiantAbsente(cne, codeModule);
    }

    @PutMapping("/updateForImage")
    public int updateForImage(@RequestBody  Absence absence) {
        return absenceService.updateForImage(absence);
    }

    @GetMapping("/Etudiant/Cne/{cne}/seance/libelle/{libelle}")
    public Absence findByEtudiantCneAndSeanceLibelle(@PathVariable String cne,@PathVariable String libelle) {
        return absenceService.findByEtudiantCneAndSeanceLibelle(cne, libelle);
    }
    @GetMapping("/seance/date/{date}")
    public List<Absence> findByEtatAbsenceTrueAndSeanceDateSeance(@PathVariable String date) {
        return absenceService.findByEtatAbsenceTrueAndSeanceDate(date);
    }

    @PutMapping("/")
    public void update(@RequestBody List<Absence> absences) {
        absenceService.update(absences);
    }


}
