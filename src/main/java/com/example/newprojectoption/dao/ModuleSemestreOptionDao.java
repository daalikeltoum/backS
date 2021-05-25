package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.ModuleSemestreOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleSemestreOptionDao extends JpaRepository<ModuleSemestreOption,Long> {
    List<ModuleSemestreOption> findByMyOptionCode(String code);
    ModuleSemestreOption findByMyOptionCodeAndMyModuleCodeAndSemestreCode(String codeOp,String codeMod,String codeSeme);
    ModuleSemestreOption findByCode(String code);
    List<ModuleSemestreOption> findBySemestreCodeAndAnneeUnversAndMyOptionCode(int codeseme,Long annee,String codeOp);

    ModuleSemestreOption findBySemestreCodeAndAnneeUnversAndMyOptionCodeAndMyModuleCode(int codeSemestre, long l, String cmyOption, String codeModule);

    int deleteByCode( String code);
    int deleteByMyOptionCode(String code);
    int deleteByMyModuleCode(String code);
}
