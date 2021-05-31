package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.AnneeUniversitaire;
import com.example.newprojectoption.service.AnneeUniversitaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ispits-project/année-universitaire")

public class AnneeUniversitaireProvided {
    @Autowired
    private AnneeUniversitaireService anneeUniversitaireService;

    @GetMapping("/")
    public List<AnneeUniversitaire> findAll() {
        return anneeUniversitaireService.findAll();
    }
}
