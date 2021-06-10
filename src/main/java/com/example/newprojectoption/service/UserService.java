package com.example.newprojectoption.service;

import com.example.newprojectoption.bean.*;
import com.example.newprojectoption.dao.AbsenceDao;
import com.example.newprojectoption.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Userr findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    public int save (Userr user){
        if(findByUsername(user.getUsername())!=null){
            return -1;
        }
        else{
            userDao.save(user);
            return 1;
        }
    }

}
