package com.example.newprojectoption.dao;


import com.example.newprojectoption.bean.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceDao extends JpaRepository<Absence,Long> {
    Absence findByEtudiantCneAndSeanceLibelle(String cne,String libelle);

}
