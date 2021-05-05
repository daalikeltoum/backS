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
    private MyModule module;     
	@ManyToOne
    private MyOption option;


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



	public MyModule getModule() {
		return module;
	}

	public void setModule(MyModule module) {
		this.module = module;
	}

	public MyOption getOption() {
		return option;
	}

	public void setOption(MyOption option) {
		this.option = option;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
}
