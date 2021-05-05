package com.example.newprojectoption.bean;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class NoteEtudiantSemestre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private BigDecimal note ;
    @ManyToOne
    private Semestre semestre;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private EtatValidation etatValidation;


    public BigDecimal getMoyenne() {
        return note;
    }

    public void setMoyenne(BigDecimal moyenne) {
        this.note = moyenne;
    }

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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EtatValidation getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(EtatValidation etatValidation) {
        this.etatValidation = etatValidation;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }
}

