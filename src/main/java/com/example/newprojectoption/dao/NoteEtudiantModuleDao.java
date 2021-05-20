package com.example.newprojectoption.dao;
import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.NoteEtudiantModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteEtudiantModuleDao extends JpaRepository<NoteEtudiantModule,Long> {
    List<NoteEtudiantModule> findByEtudiantCne(String cne);
    List<NoteEtudiantModule> findByEtatValidationCode(String code);
    List<NoteEtudiantModule> findByModuleSemestreOptionCode (String code);
    List<NoteEtudiantModule> findByModuleSemestreOptionCodeAndEtatValidationCode (String code,String codeEtat);
    List<NoteEtudiantModule> findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCne(int codeSemestre,Long anne, String cne);
    List<NoteEtudiantModule> findByModuleSemestreOptionSemestreCodeAndModuleSemestreOptionAnneeUnversAndEtudiantCneAndEtatValidationCode(int codeSemestre,Long anne, String cne,String codeEtat);

}
