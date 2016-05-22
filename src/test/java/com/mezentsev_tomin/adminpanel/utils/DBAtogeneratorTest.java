package com.mezentsev_tomin.adminpanel.utils;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.util.HibernateUtil;
import org.hibernate.Session;
import org.testng.annotations.Test;

/**
 * Created by Mezentsev.Y on 5/19/2016.
 */
public class DBAtogeneratorTest {

    //@Ignore
    @Test
    public void generateUsers(){
        final int count = 500;
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        for (long i = 1; i<=count; i++){
            User user = new User(i, "User" + i, "login" + i, "user" + i + "@gmail.com", "password" + i, "/photos/user" +
                    i +".jpg", "Hi, dude!!!");
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
