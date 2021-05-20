package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.Ponderation;
import com.example.newprojectoption.dao.PonderationDao;
import com.example.newprojectoption.service.PonderationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/ponderation")
public class PonderationProvided {
    @GetMapping("/code/{code}")
    public Ponderation findByCode(@PathVariable String code) {
        return ponderationService.findByCode(code);
    }
    @GetMapping("/coefContinue/{cne}/coefFinale/{code}")
    public List<Ponderation> findByCoefContinueAndCoefFinale(@PathVariable BigDecimal coefContinue,@PathVariable BigDecimal coefFinale) {
        return ponderationService.findByCoefContinueAndCoefFinale(coefContinue, coefFinale);
    }
    @GetMapping("/")
    public List<Ponderation> findAll() {
        return ponderationService.findAll();
    }

    @Autowired
    private PonderationService ponderationService;
}
