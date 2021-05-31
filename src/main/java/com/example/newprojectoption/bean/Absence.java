package com.example.newprojectoption.bean;


import javax.persistence.*;

@Entity
public class Absence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Etudiant etudiant;
	//private  justification;
	private boolean etatJustification;
	private boolean etatAbsence;

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

	public boolean isEtatJustification() {
		return etatJustification;
	}

	public void setEtatJustification(boolean etatJustification) {
		this.etatJustification = etatJustification;
	}

	public boolean isEtatAbsence() {
		return etatAbsence;
	}

	public void setEtatAbsence(boolean etatAbsence) {
		this.etatAbsence = etatAbsence;
	}
}
