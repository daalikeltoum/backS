package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.bean.Semestre;
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

    @Autowired
    private EtudiantOptionService etudiantOptionService;

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

    @GetMapping("/Etudiant/cne/{cne}/option/codeoption/{codeoption}/annee/{annee}/semestre/semstreCode/{semstreCode}")
    public EtudiantOption findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(String cne, String codeoption, String annee, int semstreCode) {
        return etudiantOptionService.findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(cne, codeoption, annee, semstreCode);
    }
    @PostMapping("/")
    public int save(@RequestBody EtudiantOption etudiantOption) {
        return etudiantOptionService.save(etudiantOption);
    }

    @GetMapping("/option/code/{code}")
    public List<EtudiantOption> findMyOptionCode(@PathVariable String code) {
        return etudiantOptionService.findByMyOptionCode(code);
    }

    @GetMapping("etudiant/cneetudiant/{cetudiant}/option/code/{cmyOption}/annee/{annee}/semestre/codesemestre/{csemestre}")
    public void saveEtudiantOption(@PathVariable String cetudiant,@PathVariable String cmyOption, @PathVariable String annee,@PathVariable int csemestre) {
        etudiantOptionService.saveEtudiantOption(cetudiant, cmyOption, annee, csemestre);
    }

    @GetMapping("etudiant/cneetudiant/{cetudiant}/semestre/codesemestre1/{codesemestre1}/semestre/codesemestre2/{codeSemestre2}/annee/{annee}/option/code/{cmyOption}")
    public void saveEtudiantModule(@PathVariable String cetudiant,@PathVariable  int codesemestre1, @PathVariable int codeSemestre2, @PathVariable String annee,@PathVariable  String cmyOption) {
        etudiantOptionService.saveEtudiantModule(cetudiant, codesemestre1, codeSemestre2, annee, cmyOption);
    }
    @GetMapping("semestre/codesemestre1/{codeSemsetre1}/semestre/codesemestre2/{codeSemestre2}/etudiant/cneetudiant/{cetudiant}/option/code/{cmyOption}/annee/{annee}")
    public void saveEtudiantReinscrit(@PathVariable int codeSemsetre1,@PathVariable  int codeSemestre2,@PathVariable  String cetudiant,@PathVariable  String cmyOption, @PathVariable String annee) {
        etudiantOptionService.saveEtudiantReinscrit(codeSemsetre1, codeSemestre2, cetudiant, cmyOption, annee);
    }
}
