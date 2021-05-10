package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.dao.EtudiantOptionDao;
import com.example.newprojectoption.service.EtudiantOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/etudiantOption")
public class EtudiantOptionProvided {

    /*@GetMapping("/option/code/{code}")
    public List<EtudiantOption> findByMyOptionCode(@PathVariable String code) {
        return etudiantOptionService.findByMyOptionCode(code);
    }*/
    @GetMapping("/option/code/{code}/annee/{annee}")
    public List<EtudiantOption> findByMyOptionCodeAndAnnee(@PathVariable String code,@PathVariable String annee) {
        return etudiantOptionService.findByMyOptionCodeAndAnnee(code, annee);
    }

    @DeleteMapping("/Etudiant/cne/{cne}")
    public int deleteByEtudiantCne(@PathVariable String cne) {
        return etudiantOptionService.deleteByEtudiantCne(cne);
    }

    @DeleteMapping("/option/code/{code}")
    public int deleteByMyOptionCode(@PathVariable String code) {
        return etudiantOptionService.deleteByMyOptionCode(code);
    }

    @GetMapping("/Etudiant/cne/{cne}/option/code/{code}")
    public EtudiantOption findByEtudiantCneAndMyOptionCode(@PathVariable String cne,@PathVariable String code) {
        return etudiantOptionService.findByEtudiantCneAndMyOptionCode(cne, code);
    }
    @PostMapping("/")
    public int save(@RequestBody EtudiantOption etudiantOption) {
        return etudiantOptionService.save(etudiantOption);
    }

    @GetMapping("/option/code/{code}")
    public List<EtudiantOption> findMyOptionCode(@PathVariable String code) {
        return etudiantOptionService.findByMyOptionCode(code);
    }

    @Autowired
    private EtudiantOptionService etudiantOptionService;
}
