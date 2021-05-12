package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.NoteEtudiantSemestre;
import com.example.newprojectoption.service.NoteEtudiantSemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/note-etudiant-semestre")
public class NoteEtudiantSemestreProvided {
    @Autowired
    private NoteEtudiantSemestreService noteEtudiantSemestreService;

    @GetMapping("/")
    public List<NoteEtudiantSemestre> findAll() {
        return noteEtudiantSemestreService.findAll();
    }
    /*@GetMapping("/option/code/{code}/smsetre/code1/{code1}/annee/{annee}")
    public void saveNoteSemestre(@PathVariable String code,@PathVariable String code1,@PathVariable String annee){
        noteEtudiantSemestreService.saveNoteSemestre(code,code1,annee);
    }*/

    @GetMapping("/Etudiant/cne/{cne}/Semestre/code/{code}")
    public NoteEtudiantSemestre findByEtudiantCneAndSemestreCode(@PathVariable String cne,@PathVariable String code) {
        return noteEtudiantSemestreService.findByEtudiantCneAndSemestreCode(cne, code);
    }
    @GetMapping("/Semestre/code/{code}")
    public List<NoteEtudiantSemestre> findBySemestreCode(@PathVariable String code) {
        return noteEtudiantSemestreService.findBySemestreCode(code);
    }
    @GetMapping("/Etudiant/cne/{cne}")
    public List<NoteEtudiantSemestre> findByEtudiantCne(@PathVariable String cne) {
        return noteEtudiantSemestreService.findByEtudiantCne(cne);
    }

    @GetMapping("/code/{code}")
    public NoteEtudiantSemestre findByCode(@PathVariable String code) {
        return noteEtudiantSemestreService.findByCode(code);
    }

    @GetMapping("/semestre/codeSemestre/{codeSemestre}/option/codeOption/{codeOption}/annee/{annee}")
    public List<NoteEtudiantSemestre> notesSemestre(@PathVariable String codeSemestre,@PathVariable String codeOption,@PathVariable String annee) {
        return noteEtudiantSemestreService.notesSemestre(codeSemestre, codeOption, annee);
    }
}
