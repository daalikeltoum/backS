package com.example.newprojectoption.bean;

import javax.persistence.*;

@Entity
public class EtudiantOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private MyOption myOption;
    @ManyToOne
    private Semestre semestre;
    private Long annee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public MyOption getMyOption() {
        return myOption;
    }

    public void setMyOption(MyOption myOption) {
        this.myOption = myOption;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Long getAnnee() {
        return annee;
    }

    public void setAnnee(Long annee) {
        this.annee = annee;
    }
}
