package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteEtudiantModuleDao extends JpaRepository<NoteEtudiantModule,Long> {
      List<NoteEtudiantModule> findByModuleSemestreOptionCode(String code);

}
