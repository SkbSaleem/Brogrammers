package edu.unsw.comp9321;
//kjh

import java.io.Serializable;
import java.sql.Date;

public class Credit implements Serializable{
	private String username;
	private boolean authorized;
	private String firstName;
	private String lastName;
	private String profilePic;
	private String civilStatus;
	private Date dob;
	private String gender;
	private String email;
	
	public Credit(String username, boolean authorized) {
		this.username = username;
		this.authorized = authorized;
	}
	
	public Credit(String username, boolean authorized, Date dob, String gender,
			String firstname, String lastname, String profilepic, String civilstatus, String email ) {
	this.username = username;
	this.authorized = authorized;
	this.firstName = firstname;
	this.lastName = lastname;
	this.profilePic = profilepic;
	this.civilStatus = civilstatus;
	this.dob = dob;
	this.gender = gender;
	this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	

}
