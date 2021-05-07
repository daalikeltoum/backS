package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.Etudiant;
import com.example.newprojectoption.bean.EtudiantOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantOptionDao extends JpaRepository<EtudiantOption,Long> {
    List<EtudiantOption> findByMyOptionCode(String code);
    EtudiantOption findByEtudiantCneAndMyOptionCode(String cne,String code);
    List<EtudiantOption> findByMyOptionCodeAndAnnee(String code,String annee);
    int deleteByEtudiantCne(String cne);
    int deleteByMyOptionCode(String code);
}
