package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.beans.User;

import java.util.Collection;

/**
 * Created by Mezentsev.Y on 5/19/2016.
 */
public interface UserDao {

    void create(User user);
    void update(User user);
    void delete(Long id);
    User findByUserName(String username);
    User findByEmail(String email);
    User findById(Long id);
    Collection<User> findAll();
}
