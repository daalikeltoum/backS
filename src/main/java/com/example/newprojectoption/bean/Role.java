package com.example.newprojectoption.bean;

import javax.persistence.*;

@Entity
public class Role {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Enumerated(EnumType.STRING)
    private String name;

    public Role() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
