package com.mezentsev_tomin.adminpanel.dao.impl;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.dao.UserDao;
import com.mezentsev_tomin.adminpanel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mezentsev.Y on 5/19/2016.
 */

@Repository
public class UserDaoImpl implements UserDao {

    public void create(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void update(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void delete(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete((User)session.load(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public User findByUserName(String username) {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> users =  session.createCriteria(User.class).add(Restrictions.eq("username", username)).list();
        if (session.isOpen()) {
            session.close();
        }
        return users.size()>0 ? users.get(0) : null;
    }

    @Override
    public User findByEmail(String email) {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createCriteria(User.class).add(Restrictions.eq("email", email)).list();
        if (session.isOpen()) {
            session.close();
        }
        return users.size()>0 ? users.get(0) : null;

    }

    public User findById(Long id) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public Collection<User> findAll() {
        Session session = null;
        List<User> users = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }
}
