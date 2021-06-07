package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.Absence;
import com.example.newprojectoption.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("ispits-project/absence")

public class AbsenceProvided {
    @GetMapping("/Etudiant/Cne/{Cne}/seance/libelle/{libelle}")
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

    @Autowired
    private AbsenceService absenceService;

    @PostMapping("/")
    public int save(@RequestBody  List<Absence> absences) {
        return absenceService.save(absences);
    }
    /*@GetMapping("/seance/date/{date}")
    public List<Absence> findBySeanceDateSeance(@PathVariable Date date) {
        return absenceService.findBySeanceDateSeance(date);
    }*/
}
