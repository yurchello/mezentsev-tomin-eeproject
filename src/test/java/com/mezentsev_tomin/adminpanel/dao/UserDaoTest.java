package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.beans.User;


import com.mezentsev_tomin.adminpanel.util.HibernateUtil;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Yuriy on 22.05.2016.
 */
@ContextConfiguration("classpath:test-application-context.xml")
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserDao userDao;

    @BeforeClass
    public void init(){

    }

    @Test(priority = 1)
    public void create() throws Exception {
        User user = new User(6662l,
                "User666",
                "user666",
                "666@.gmail.com",
                "1111",
                "/photo/p.jpg",
                "someinfo" );
        userDao.create(user);
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user2 = (User) session.get(User.class, 6662l);
        if (session.isOpen()) session.close();
        Assert.assertNotNull(user2);
    }

    @Test(priority = 2)
    public void findById() throws Exception {

    }
//
//    @Test(priority = 3)
//    public void findByEmail() throws Exception {
//
//    }
//
//    @Test(priority = 4)
//    public void findByLogin() throws Exception {
//
//    }
//
//    @Test(priority = 5)
//    public void findAll() throws Exception {
//
//    }
//
//
//    @Test(priority = 6)
//    public void update() throws Exception {
//
//    }
//
//    @Test(priority = 7)
//    public void delete() throws Exception {
//
//    }


    private List<User> generateUsers(long from, long to){
        List<User> list = new ArrayList<>();
        while (from<=to){
            User user = new User(from, "User" + from, "login" + from, "user" + from + "@gmail.com", "password" + from, "/photos/user" +
                    from +".jpg", "Hi, dude!!!");
            list.add(user);
        }
        return list;
    }


}