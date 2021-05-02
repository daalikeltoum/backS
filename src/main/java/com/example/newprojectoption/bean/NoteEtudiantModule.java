package com.example.newprojectoption.bean;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class NoteEtudiantModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @ManyToOne
    private MyModule myModule;
    private BigDecimal moyenne ;
    private BigDecimal noteContinue;
    private BigDecimal noteFinal;
    @ManyToOne
    private Etudiant etudiant;
    //private EtatValidation etatValidation;

    public BigDecimal getNoteContinue() {
        return noteContinue;
    }

    public void setNoteContinue(BigDecimal noteContinue) {
        this.noteContinue = noteContinue;
    }

    public BigDecimal getNoteFinal() {
        return noteFinal;
    }

    public void setNoteFinal(BigDecimal noteFinal) {
        this.noteFinal = noteFinal;
    }

    public MyModule getMyModule() {
        return myModule;
    }

    public void setMyModule(MyModule myModule) {
        this.myModule = myModule;
    }

    public BigDecimal getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(BigDecimal moyenne) {
        this.moyenne = moyenne;
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


}

