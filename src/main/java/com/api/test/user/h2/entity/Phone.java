package com.api.test.user.h2.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="phone")
public class Phone implements Serializable{

	private static final long serialVersionUID = -5005890044683279434L;


	@Id
	@GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name ="id_phone")
//	private String idPhone;
	private UUID idPhone;
	
	
//	@ManyToOne
//	@ManyToOne(cascade = { CascadeType.ALL, CascadeType.MERGE,	CascadeType.REMOVE }, fetch = FetchType.EAGER)
//	@JoinColumn(name = "user_uuid")
	private UUID user_id;
//	private String user_id;
	
	@Column(name ="NUMBER", length = 7)
	private int number;
	
	@Column(name ="CITY_CODE", length = 1)
	private short citycode;
	
	
	@Column(name = "COUNTRY_CODE", length = 2)
	private short contrycode;


	public UUID getIdPhone() {
		return idPhone;
	}


	public void setIdPhone(UUID idPhone) {
		this.idPhone = idPhone;
	}


	public UUID getUser_id() {
		return user_id;
	}


	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public short getCitycode() {
		return citycode;
	}


	public void setCitycode(short citycode) {
		this.citycode = citycode;
	}


	public short getContrycode() {
		return contrycode;
	}


	public void setContrycode(short contrycode) {
		this.contrycode = contrycode;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
