package com.mezentsev_tomin.adminpanel.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 5/19/2016.
 */

@Entity
@Table(name = "user")
public class User {

    //@Id
    @Column(name="id")
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    private Long id;
    @Id
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="photoPath")
    private String photo_path;
    @Column(name="description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public User(Long id, String username, String email, String password, String photo_path, String description) {

        this.id = id;

        this.username = username;
        this.email = email;
        this.password = password;
        this.photo_path = photo_path;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + getId() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + username + '\'' +
                ", photo_path='" + photo_path + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


