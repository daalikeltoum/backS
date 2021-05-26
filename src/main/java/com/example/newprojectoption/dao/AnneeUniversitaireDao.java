package com.example.newprojectoption.dao;


import com.example.newprojectoption.bean.AnneeUniversitaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnneeUniversitaireDao extends JpaRepository<AnneeUniversitaire,Long> {

   AnneeUniversitaire findByLibelle(String libelle);
   AnneeUniversitaire findByAnneeOne(Long annee);
}
