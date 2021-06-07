package com.example.newprojectoption.dao;


import com.example.newprojectoption.bean.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AbsenceDao extends JpaRepository<Absence,Long> {
    Absence findByEtudiantCneAndSeanceLibelle(String cne,String libelle);
    List<Absence> findByEtatAbsenceTrueAndSeanceDateSeance(String date);
    //List<Absence> findBySeanceDateSeance(Date date);

}
