package com.example.newprojectoption.dao;
import com.example.newprojectoption.bean.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AbsenceDao extends JpaRepository<Absence,Long> {
    Absence findByEtudiantCneAndSeanceLibelle(String cne,String libelle);
    List<Absence> findByEtudiantCneAndSeanceModuleSemestreOptionSemestreCodeAndSeanceModuleSemestreOptionAnneeUniversitaireLibelle(String cne, int semestre,String anne);
    @Query("select a from Absence a where a.etudiant.cne=:cne and a.seance.moduleSemestreOption.code =:codeModule and (a.etatJustification='refuse' or a.etatJustification='Aucune justification donnee' )")
    List<Absence> findEtudiantAbsente(@Param("cne") String cne,@Param("codeModule") String codeModule);

  }
