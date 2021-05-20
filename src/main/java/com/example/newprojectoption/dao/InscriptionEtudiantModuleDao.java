package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.EtudiantOption;
import com.example.newprojectoption.bean.InscriptionEtudiantModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionEtudiantModuleDao extends JpaRepository<InscriptionEtudiantModule,Long> {
    InscriptionEtudiantModule findByCode(String code);

}
