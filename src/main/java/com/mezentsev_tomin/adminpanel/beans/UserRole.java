package com.mezentsev_tomin.adminpanel.beans;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Yuriy on 26.06.2016.
 */
@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @Column(name = "role", nullable = false, length = 45)
    private String role;

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public UserRole() {
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
