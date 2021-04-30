package com.example.newprojectoption.bean;

import javax.persistence.*;

@Entity
public class MyModule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String libelle;
	private String code;
	@ManyToOne
	private MyOption myOption;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public MyOption getOption() {
		return myOption;
	}

	public void setOption(MyOption myOption) {
		this.myOption = myOption;
	}

}
