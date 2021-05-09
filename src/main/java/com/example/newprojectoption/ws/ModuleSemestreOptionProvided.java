package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.ModuleSemestreOption;
import com.example.newprojectoption.bean.MyModule;
import com.example.newprojectoption.service.ModuleSemestreOptionService;
import com.example.newprojectoption.service.MyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/semestre/code/{codeseme}/anneeuniv/{annee}/option/code/{codeOp}")
    public List<ModuleSemestreOption> findBySemestreCodeAndAnneeUnversAndMyOptionCode(@PathVariable String codeseme,@PathVariable String annee,@PathVariable String codeOp) {
        return moduleSemestreOptionService.findBySemestreCodeAndAnneeUnversAndMyOptionCode(codeseme, annee, codeOp);
    }

    @GetMapping("/")
    public List<ModuleSemestreOption> findAll() {
        return moduleSemestreOptionService.findAll();
    }

}
