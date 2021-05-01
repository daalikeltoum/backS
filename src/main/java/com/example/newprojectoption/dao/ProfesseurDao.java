package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfesseurDao extends JpaRepository<Professeur,Long> {
    Professeur findByCin(String cin);
    Professeur findByCodeEmploye( String codeEmploye);
    List<Professeur> findByNomAndPrenom(String nom,String prenon);
    int deleteByCodeEmploye ( String codeEmploye);
}
