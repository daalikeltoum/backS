package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.MyOption;
import com.example.newprojectoption.service.MyOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/option")
public class MyOptionProvided {
    @Autowired
    private MyOptionService myOptionService;

    @GetMapping("/code/{code}")
    public MyOption findByCode(@PathVariable String code) {
        return myOptionService.findByCode(code);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return myOptionService.deleteByCode(code);
    }
    @GetMapping("/")
    public List<MyOption> findAll() {
        return myOptionService.findAll();
    }
    @PostMapping("/")
    public void save(@RequestBody MyOption myOption) {
        myOptionService.save(myOption);
    }
    @GetMapping("/filiere/code/{code}")
    public List<MyOption> findByFilliereCode(@PathVariable String code) {
        return myOptionService.findByFilliereCode(code);
    }
    @PutMapping("/")
    public void update(@RequestBody MyOption myOption) {
        myOptionService.update(myOption);
    }
}
