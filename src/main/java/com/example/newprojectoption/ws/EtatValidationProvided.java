package com.example.newprojectoption.ws;



import com.example.newprojectoption.bean.EtatValidation;
import com.example.newprojectoption.service.EtatValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/etat-validation")

public class EtatValidationProvided {
    @Autowired
    private EtatValidationService etatValidationService;

    @GetMapping("/code/{code}")
    public EtatValidation findByCode(@PathVariable  String code) {
        return etatValidationService.findByCode(code);
    }
    @GetMapping("/libelle/{libelle}")
    public EtatValidation findByLibelle(@PathVariable String libelle) {
        return etatValidationService.findByLibelle(libelle);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return etatValidationService.deleteByCode(code);
    }
    @GetMapping("/")
    public List<EtatValidation> findAll() {
        return etatValidationService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody EtatValidation etatValidation) {
        return etatValidationService.save(etatValidation);
    }
}
