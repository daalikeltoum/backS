package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.EtudiantOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantOptionDao extends JpaRepository<EtudiantOption,Long> {
    List<EtudiantOption> findByMyOptionCode(String code);
    EtudiantOption findByEtudiantCneAndMyOptionCodeAndAnneeAndSemestreCode(String cne,String codeoption,Long anne ,int semstreCode);
    List<EtudiantOption> findByMyOptionCodeAndAnneeAndSemestreCode(String code,Long annee,int codeSems);
    int deleteByEtudiantCne(String cne);
    int deleteByMyOptionCode(String code);
}
