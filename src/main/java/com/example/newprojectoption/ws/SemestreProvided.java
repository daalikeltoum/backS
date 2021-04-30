package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.Semestre;
import com.example.newprojectoption.service.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ispits-project/semestre")
public class SemestreProvided {
    @Autowired
    private SemestreService semestreService;

    @GetMapping("code/{code}")
    public Semestre findByCode(@PathVariable String code) {
        return semestreService.findByCode(code);
    }

    @GetMapping("libelle/{libelle}")
    public Semestre findByLibelle(@PathVariable String libelle) {
        return semestreService.findByLibelle(libelle);
    }

    @DeleteMapping("code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return semestreService.deleteByCode(code);
    }

    @GetMapping("/")
    public List<Semestre> findAll() {
        return semestreService.findAll();
    }


    @PostMapping("/")
    public int save(@RequestBody Semestre semestre) {
        return semestreService.save(semestre);
    }
}
