package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.beans.User;


import com.mezentsev_tomin.adminpanel.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
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

    private Long id;
    @Test(priority = 1)
    public void create() throws Exception {
        Assert.assertNotNull(userDao);
        User user = new User();
        user.setUsername("user666");
        user.setEmail("66@.gmail.com");
        user.setPassword("1111");
        user.setPhoto_path("/photo/p.jpg");
        user.setDescription("someinfo");
        //user.setId(666l);
        userDao.create(user);
        this.id = user.getId();
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user2 = (User) session.get(User.class, user.getId());
        if (session.isOpen()) session.close();
        Assert.assertNotNull(user2);
    }

    @Test(priority = 2)
    public void findById() throws Exception {
        User user = userDao.findById(this.id);
        Assert.assertNotNull(user);
    }

    @Test(priority = 3)
    public void findAll() throws Exception {
        Collection<User> list = userDao.findAll();
        Assert.assertTrue(list.size()>0);
    }


    @Test(priority = 4)
    public void update() throws Exception {
        final String EMAIL = "email@nameChanged";

        User user = userDao.findById(this.id);
        Assert.assertNotNull(user);
        user.setEmail(EMAIL);
        userDao.update(user);
        Assert.assertEquals(EMAIL, userDao.findById(this.id).getEmail());
    }
    @Test(priority = 5)
    public void testFindByUserName() throws Exception {
        final String LOGIN = "user666";
        User user = userDao.findByUserName(LOGIN);
        Assert.assertNotNull(user);

    }

    @Test(priority = 6)
    public void testFindByEmail() throws Exception {
        final String EMAIL = "66@.gmail.com";
        User user = userDao.findByEmail(EMAIL);
        Assert.assertNotNull(user);

    }

    @Test(priority = 7)
    public void delete() throws Exception {
        userDao.delete(this.id);
    }


    private List<User> generateUsers(long from, long to){
        List<User> list = new ArrayList<User>();
//        while (from<=to){
//            User user = new User(from, "User" + from, "login" + from, "user" + from + "@gmail.com", "password" + from, "/photos/user" +
//                    from +".jpg", "Hi, dude!!!");
//            list.add(user);
//        }
       return list;
    }


}