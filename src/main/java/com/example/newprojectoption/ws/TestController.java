package com.example.newprojectoption.ws;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
@CrossOrigin(origins = "*",maxAge=3600)
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('PROF') or hasRole('COORDONNATEUR') or hasRole('ADMIN')")
    public String userAccess() {
        return "Prof Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('COORDONNATEUR')")
    public String moderatorAccess() {
        return "COORDONNATEUR Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
