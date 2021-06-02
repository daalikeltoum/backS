package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.Absence;
import com.example.newprojectoption.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ispits-project/absence")

public class AbsenceProvided {
    @Autowired
    private AbsenceService absenceService;

    @PostMapping("/")
    public int save(@RequestBody  List<Absence> absences) {
        return absenceService.save(absences);
    }
  }
