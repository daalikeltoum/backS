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
	@JsonFormat(pattern="HH:mm", timezone = "UTC")
	private Date heureDebut;
	@JsonFormat(pattern="HH:mm", timezone = "UTC")
	private Date heureFin;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
	private Date date;


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

	public Date getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
