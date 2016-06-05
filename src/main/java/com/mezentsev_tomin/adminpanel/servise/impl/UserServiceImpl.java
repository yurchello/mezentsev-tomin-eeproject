package com.mezentsev_tomin.adminpanel.servise.impl;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.dao.UserDao;
import com.mezentsev_tomin.adminpanel.exceptions.InvalidUserInputException;
import com.mezentsev_tomin.adminpanel.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Yuriy on 29.05.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void create(User user) {
        this.userDao.create(user);
    }

    @Transactional
    @Override
    public User login(String login, String password) throws InvalidUserInputException {
        User user = userDao.findByEmail(login);
        if (user == null)throw new InvalidUserInputException("No user with login: " + login);
        if (!user.getPassword().equals(password))throw new InvalidUserInputException("Not correct password");
        return user;
    }

    @Transactional
    @Override
    public User signUp(String name, String email, String login, String password) throws InvalidUserInputException {
        User user = new User();
        if (this.userDao.findByLogin(login) == null) {
            user.setLogin(login);
        }else throw new InvalidUserInputException("This login already exist!");
        if (this.userDao.findByEmail(email) == null) {
            user.setEmail(email);
        }else throw new InvalidUserInputException("You've already used this e-mail!");
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    @Transactional
    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.userDao.delete(id);
    }

    @Transactional
    @Override
    public User findByLogin(String login) {
        return this.userDao.findByLogin(login);
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return this.userDao.findById(id);
    }

    @Transactional
    @Override
    public Collection<User> findAll() {
        return this.userDao.findAll();
    }
}
