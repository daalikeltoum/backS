package com.example.newprojectoption.bean;
import javax.persistence.*;
@Entity
public class ModuleSemestreOption {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String anneeUnvers;
	@ManyToOne
    private Semestre semestre;
	@ManyToOne
    private MyModule mymodule;
	@ManyToOne
    private MyOption myoption;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAnneeUnvers() {
		return anneeUnvers;
	}

	public void setAnneeUnvers(String anneeUnvers) {
		this.anneeUnvers = anneeUnvers;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public MyModule getMymodule() {
		return mymodule;
	}

	public void setMymodule(MyModule mymodule) {
		this.mymodule = mymodule;
	}

	public MyOption getMyoption() {
		return myoption;
	}

	public void setMyoption(MyOption myoption) {
		this.myoption = myoption;
	}
}
