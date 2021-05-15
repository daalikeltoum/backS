package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.ModuleSemestreOption;
import com.example.newprojectoption.bean.MyModule;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import com.example.newprojectoption.service.NoteEtudiantModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/note-etudiant-modul")
public class NoteEtudiantModuleProvided {



    @Autowired
    private NoteEtudiantModuleService noteEtudiantModuleService;

    @GetMapping("/")
    public List<NoteEtudiantModule> findAll() {
        return noteEtudiantModuleService.findAll();
    }
    @GetMapping("/module-semestre-option/codeModule/{codeModule}/option/codeOption/{codeOption}")
    public List<NoteEtudiantModule> findNotes(@PathVariable String codeModule,@PathVariable String codeOption) {
        return noteEtudiantModuleService.findNotes(codeModule, codeOption);
    }

    @PutMapping("/")
    public void update(@RequestBody NoteEtudiantModule noteEtudiantModule) {
        noteEtudiantModuleService.update(noteEtudiantModule);
    }
    @GetMapping("moduleSemestreOption/semestre/codeSemestre/{codeSemestre}/moduleSemestreOption/annee/{annee}/Etudiant/cne/{cne}")
    public List<NoteEtudiantModule> findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(@PathVariable int codeSemestre,@PathVariable String annee,@PathVariable String cne) {
        return noteEtudiantModuleService.findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(codeSemestre, annee, cne);
    }
}
