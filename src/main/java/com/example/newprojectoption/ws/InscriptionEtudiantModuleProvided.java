package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.AnneeUniversitaire;
import com.example.newprojectoption.bean.InscriptionEtudiantModule;
import com.example.newprojectoption.service.AnneeUniversitaireService;
import com.example.newprojectoption.service.InscriptionEtudiantModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ispits-project/inscriptionEtudiantModule")

public class InscriptionEtudiantModuleProvided {
    @Autowired
    private InscriptionEtudiantModuleService inscriptionEtudiantModuleService;

    @GetMapping("/moduleSemestreOption/code/{code}")
    public List<InscriptionEtudiantModule> findByModuleSemestreOptionCode(@PathVariable String code) {
        return inscriptionEtudiantModuleService.findByModuleSemestreOptionCode(code);
    }

}
