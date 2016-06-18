package com.mezentsev_tomin.adminpanel.servise;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.exceptions.InvalidUserInputException;

import java.util.Collection;

/**
 * Created by Yuriy on 29.05.2016.
 */
public interface UserService {
    void create(User user);
    User login (String login, String password) throws InvalidUserInputException;
    User signUp(String name, String email, String login, String password, String photoPath, String description) throws InvalidUserInputException;
    void update(User user);
    void delete(Long id);
    User findByLogin(String login);
    User findByEmail(String email);
    User findById(Long id);
    Collection<User> findAll();
}
