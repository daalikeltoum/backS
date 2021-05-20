package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.EtatValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EtatValidationDao extends JpaRepository<EtatValidation,Long> {

    EtatValidation findByCode(String code);
    EtatValidation findByLibelle( String libelle);
    int deleteByCode( String code);

}