package com.example.newprojectoption.bean;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class NoteEtudiantEpreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private BigDecimal note ;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEpreuve;
    @ManyToOne
    private Epreuve epreuve;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private EtatValidation etatValidation;
    @ManyToOne
    private NoteEtudiantModule noteEtudiantModule;
    @ManyToOne
    private TypeNoteEpreuve typeNoteEpreuve;

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

    public Date getDateEpreuve() {
        return dateEpreuve;
    }

    public void setDateEpreuve(Date dateEpreuve) {
        this.dateEpreuve = dateEpreuve;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public NoteEtudiantModule getNoteEtudiantModule() {
        return noteEtudiantModule;
    }

    public void setNoteEtudiantModule(NoteEtudiantModule noteEtudiantModule) {
        this.noteEtudiantModule = noteEtudiantModule;
    }

    public TypeNoteEpreuve getTypeNoteEpreuve() {
        return typeNoteEpreuve;
    }

    public void setTypeNoteEpreuve(TypeNoteEpreuve typeNoteEpreuve) {
        this.typeNoteEpreuve = typeNoteEpreuve;
    }
}


