package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.ModuleSemestreOption;
import com.example.newprojectoption.service.ModuleSemestreOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/module-semestre-option")
public class ModuleSemestreOptionProvided {
    @Autowired
    private ModuleSemestreOptionService moduleSemestreOptionService;

    @GetMapping("/code/{code}")
    public List<ModuleSemestreOption> findByMyOptionCode(@PathVariable String code) {
        return moduleSemestreOptionService.findByMyOptionCode(code);
    }
    @PostMapping("/")
    public int save(@RequestBody  ModuleSemestreOption moduleSemestreOption) {
        return moduleSemestreOptionService.save(moduleSemestreOption);
    }

    @GetMapping("/")
    public List<ModuleSemestreOption> findAll() {
        return moduleSemestreOptionService.findAll();
    }

    @GetMapping("/semestre/code/{codeSemestre}/anneeuniv/libelle/{annee}/option/code/{codeOp}")
    public List<ModuleSemestreOption> findBySemestreCodeAndAnneeUniversitaireLibelleAndMyOptionCode(@PathVariable int codeSemestre,@PathVariable String annee,@PathVariable String codeOp) {
        return moduleSemestreOptionService.findBySemestreCodeAndAnneeUniversitaireLibelleAndMyOptionCode(codeSemestre, annee, codeOp);
    }

    @GetMapping("/semestre/code/{codeSemestre}/anneeuniv/anneeOne/{annee}/option/code/{cmyOption}")
    public List<ModuleSemestreOption> findBySemestreCodeAndAnneeUniversitaireAnneeOneAndMyOptionCode(@PathVariable int codeSemestre,@PathVariable  Long annee,@PathVariable  String cmyOption) {
        return moduleSemestreOptionService.findBySemestreCodeAndAnneeUniversitaireAnneeOneAndMyOptionCode(codeSemestre, annee, cmyOption);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return moduleSemestreOptionService.deleteByCode(code);
    }
}
