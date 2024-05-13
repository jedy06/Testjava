package com.api.test.user.h2.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -7316201066531612540L;

	@Id
//	@GeneratedValue(generator = "uuid", strategy = GenerationType.TABLE) // GenerationType.TABLE
	@GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO) // GenerationType.TABLE
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id_user")
//	private String idUser;
	private UUID idUser;

	@Column(name = "name")
	private String username;
//		private String name;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "created")
	private Date created;

	@Column(name = "modified")
	private Date modified;

	@Column(name = "last_login")
	private Date last_login;

	@Column(name = "is_active")
	private boolean enabled;
//		private boolean isActive;
	
	@Lob
	@Column(name = "token")
	private String token;

	@OneToMany(mappedBy = "user_id", cascade = { CascadeType.ALL, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Phone> phones;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuarios_roles", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
			@JoinColumn(name = "id_rol") }, uniqueConstraints = {
					@UniqueConstraint(columnNames = { "id_user", "id_rol" }) })
	@JsonIgnore
	private List<Rol> roles;


	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
