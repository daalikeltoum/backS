package com.example.newprojectoption.dao;


import com.example.newprojectoption.bean.AnneeUniversitaire;
import java.util.List;
import com.example.newprojectoption.bean.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceDao extends JpaRepository<Seance,Long> {
     Seance findByLibelle(String libelle);
     List<Seance> findByModuleSemestreOptionCode(String code);

}
