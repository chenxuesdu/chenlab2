package com.cmpe275.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="profile")
public class Profile {
	@Id
	@NotNull
	private String id;
	@NotNull
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	private String organization;
	private String aboutmyself;

	public Profile() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAboutMyself() {
		return aboutmyself;
	}

	public void setAboutMyself(String aboutMyself) {
		this.aboutmyself = aboutMyself;
	}

}
