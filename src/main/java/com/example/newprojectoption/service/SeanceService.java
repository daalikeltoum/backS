package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.AnneeUniversitaire;
import com.example.newprojectoption.bean.ModuleSemestreOption;
import com.example.newprojectoption.bean.Seance;
import com.example.newprojectoption.dao.AnneeUniversitaireDao;

import com.example.newprojectoption.dao.SeanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceService {
    @Autowired
    private SeanceDao seanceDao;
    @Autowired
    private ModuleSemestreOptionService moduleSemestreOptionService;

    public int save (Seance seance){
        if(findByLibelle(seance.getLibelle())!=null){
            return -1;
        }else{
            ModuleSemestreOption moduleSemestreOption=moduleSemestreOptionService.findByCode(seance.getModuleSemestreOption().getCode());
            if(moduleSemestreOption==null)
                return -2;
            seance.setModuleSemestreOption(moduleSemestreOption);
            seanceDao.save(seance);
            return 1;
        }
    }

    public Seance findByLibelle(String libelle) {
        return seanceDao.findByLibelle(libelle);
    }

    public List<Seance> findByModuleSemestreOptionCode(String code) {
        return seanceDao.findByModuleSemestreOptionCode(code);
    }
    public void update(Seance seance){
        Seance newSeance=seanceDao.findByLibelle(seance.getLibelle());
        newSeance.setDateSeance(seance.getDateSeance());
        newSeance.setHeureDebut(seance.getHeureDebut());
        newSeance.setHeureFin(seance.getHeureFin());
        newSeance.setLibelle(seance.getLibelle());
        newSeance.setModuleSemestreOption(seance.getModuleSemestreOption());
        seanceDao.save(newSeance);
    }
}
