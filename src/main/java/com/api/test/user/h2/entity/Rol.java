package com.api.test.user.h2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol implements Serializable{

	private static final long serialVersionUID = -1982824479471012768L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "rol_name")
    private String rolName;

    private String description;



	public Integer getIdRol() {
		return idRol;
	}



	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}



	public String getRolName() {
		return rolName;
	}



	public void setRolName(String rolName) {
		this.rolName = rolName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
   
    

}
