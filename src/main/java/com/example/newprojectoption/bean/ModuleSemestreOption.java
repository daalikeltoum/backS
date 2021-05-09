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
    private MyModule myModule;
	@ManyToOne
    private MyOption myOption;
	@ManyToOne
	private  TypeModule typeModule;

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

	public MyModule getMyModule() {
		return myModule;
	}

	public void setMyModule(MyModule mymodule) {
		this.myModule = mymodule;
	}

	public MyOption getMyOption() {
		return myOption;
	}

	public void setMyOption(MyOption myoption) {
		this.myOption = myoption;
	}

	public TypeModule getTypeModule() {
		return typeModule;
	}

	public void setTypeModule(TypeModule typeModule) {
		this.typeModule = typeModule;
	}
}
