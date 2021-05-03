package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.MyModule;
import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteEtudiantModuleDao extends JpaRepository<NoteEtudiantModule,Long> {
    NoteEtudiantModule findByCode(String code);
    List<MyModule> findByMyModuleCode(String code);
    List<Etudiant> findByEtudiantCne(String cne);

}
