package com.example.newprojectoption.bean;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Epreuve {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String libelle;
	@ManyToOne
	private ModuleSemestreOption moduleSemestreOption;
	private BigDecimal coef;


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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ModuleSemestreOption getModuleSemestreOption() {
		return moduleSemestreOption;
	}

	public void setModuleSemestreOption(ModuleSemestreOption moduleSemestreOption) {
		this.moduleSemestreOption = moduleSemestreOption;
	}

	public BigDecimal getCoef() {
		return coef;
	}

	public void setCoef(BigDecimal coef) {
		this.coef = coef;
	}
}
