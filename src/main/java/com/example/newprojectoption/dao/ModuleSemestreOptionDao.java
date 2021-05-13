package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.ModuleSemestreOption;
import com.example.newprojectoption.bean.MyModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleSemestreOptionDao extends JpaRepository<ModuleSemestreOption,Long> {
    List<ModuleSemestreOption> findByMyOptionCode(String code);
    ModuleSemestreOption findByMyOptionCodeAndMyModuleCodeAndSemestreCode(String codeOp,String codeMod,String codeSeme);
    ModuleSemestreOption findByCode(String code);
    List<ModuleSemestreOption> findBySemestreCodeAndAnneeUnversAndMyOptionCode(int codeseme,String annee,String codeOp);
}
