package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.MyModule;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import com.example.newprojectoption.service.NoteEtudiantModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/noteEtudiantModule")
public class NoteEtudiantModuleProvided {
    @GetMapping("/MyModule/code/{code}")
    public List<MyModule> findByMyModuleCode(@PathVariable String code) {
        return noteEtudiantModuleService.findByMyModuleCode(code);
    }
    @GetMapping("/Etudiant/cne/{cne}")
    public List<Etudiant> findByEtudiantCne(@PathVariable String cne) {
        return noteEtudiantModuleService.findByEtudiantCne(cne);
    }
    @GetMapping("/code/{code}")
    public NoteEtudiantModule findByCode(@PathVariable String code) {
        return noteEtudiantModuleService.findByCode(code);
    }
    @PostMapping("/")
    public int save(@RequestBody NoteEtudiantModule noteEtudiantModule) {
        return noteEtudiantModuleService.save(noteEtudiantModule);
    }

    @Autowired
    private NoteEtudiantModuleService noteEtudiantModuleService;
}
