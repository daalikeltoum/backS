package com.example.newprojectoption.ws;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("ispits-project/api/v1")
public class AuthController {
    @GetMapping("/login")
    public void login() {

    }
}

