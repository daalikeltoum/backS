package com.example.newprojectoption.bean;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Seance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private ModuleSemestreOption moduleSemestreOption;
	private String heureDebut;
	private String heureFin;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
	private Date dateSeance;
	private  String libelle;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModuleSemestreOption getModuleSemestreOption() {
		return moduleSemestreOption;
	}

	public void setModuleSemestreOption(ModuleSemestreOption moduleSemestreOption) {
		this.moduleSemestreOption = moduleSemestreOption;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}


	public Date getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
