package com.mezentsev_tomin.adminpanel.beans;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Mezentsev.Y on 5/19/2016.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @Generated("default")
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="login")
    private String login;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="photo_path")
    private String photo_path;
    @Column(name="description")
    private String description;

    public User(Long id, String name, String login, String email, String password, String photo_path, String description) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.photo_path = photo_path;
        this.description = description;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_user) {
        this.id = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", photo_path='" + photo_path + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


