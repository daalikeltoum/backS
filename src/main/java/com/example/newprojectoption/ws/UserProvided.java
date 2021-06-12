package com.example.newprojectoption.ws;

import com.example.newprojectoption.bean.Userr;
import com.example.newprojectoption.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ispits-project/user")

public class UserProvided {
    @Autowired
    private UserService userService;

    @GetMapping("/username/{username}")
    public Userr findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
    @PostMapping("/")
    public int save(@RequestBody  Userr user) {
        return userService.save(user);
    }

}
