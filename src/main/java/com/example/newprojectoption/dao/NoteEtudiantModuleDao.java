package com.example.newprojectoption.dao;
import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteEtudiantModuleDao extends JpaRepository<NoteEtudiantModule,Long> {
    List<NoteEtudiantModule> findByEtudiantCne(String cne);
    List<NoteEtudiantModule> findByModuleSemestreOptionCode (String code);

    List<NoteEtudiantModule> findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(String codeSemestre,String anne, String cne);


}
