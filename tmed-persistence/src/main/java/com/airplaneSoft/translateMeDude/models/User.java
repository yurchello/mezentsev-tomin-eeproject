package com.airplaneSoft.translateMeDude.models;



import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="USER")
public class User implements Serializable{

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Pattern(regexp = "^[A-Za-z0-9]+$")
	@NotEmpty
	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;

	@Pattern(regexp = "^[A-Za-z0-9]+$")
	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	private String password;

	@Pattern(regexp = "^[A-Za-z]+$")
	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@Pattern(regexp = "^[A-Za-z]+$")
	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	//@Email
	@Column(name="EMAIL", nullable=false)
	private String email;

//	@NotEmpty
//	@Column(name = "TESTCOL", nullable=false)
//	private String testcol;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PHOTO")
	private String photo;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE",
             joinColumns = { @JoinColumn(name = "USER_ID") },
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "user_w_group",
//			joinColumns = { @JoinColumn(name = "USER_ID") },
//			inverseJoinColumns = { @JoinColumn(name = "W_GROUP_ID") })
//	private Set<WordsGroup> wordsGroups = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

//	public Set<WordsGroup> getWordsGroups() {
//		return wordsGroups;
//	}
//
//	public void setWordsGroups(Set<WordsGroup> wordsGroups) {
//		this.wordsGroups = wordsGroups;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
//	@Override
	public String toString2() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}

//	public String getTestcol() {
//		return testcol;
//	}
//
//	public void setTestcol(String testcol) {
//		this.testcol = testcol;
//	}

	@Override
	public String toString() {
		return "User [id=" + id +
				", ssoId=" + ssoId +
				", password=" +password +
				", firstName=" + firstName  +
				", lastName=" + lastName +
				", email=" + email +
//				", testcol" + testcol +
				", description='" + description  +
				", photo='" + photo +
				", userProfiles=" + userProfiles +
				"]";
	}
}
