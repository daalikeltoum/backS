package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.AnneeUniversitaire;
import com.example.newprojectoption.bean.Seance;
import com.example.newprojectoption.service.AnneeUniversitaireService;
import com.example.newprojectoption.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ispits-project/seance")

public class SeanceProvided {
    @Autowired
    private SeanceService seanceService;

    @PostMapping("/")
    public int save(@RequestBody Seance seance) {
        return seanceService.save(seance);
    }
    @GetMapping("libelle/{libelle}")
    public Seance findByLibelle(@PathVariable String libelle) {
        return seanceService.findByLibelle(libelle);
    }
    @GetMapping("/moduleSemestreOption/code/{code}")
    public List<Seance> findByModuleSemestreOptionCode(@PathVariable String code) {
        return seanceService.findByModuleSemestreOptionCode(code);
    }
}
