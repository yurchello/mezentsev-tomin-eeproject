package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/2/2016.
 */
public class UserDaoImplTest extends EntityDaoImplTest{
    @Autowired
    UserDao userDao;

    @Autowired
    UserProfileDao userProfileDao;

    @Test(priority = 10)
    public void testSave() throws Exception {
        User user = new User();
        user.setSsoId("TestNGUserSSOID");
        user.setPassword("TestNGUserPassword");
        user.setFirstName("TestNGUserName");
        user.setLastName("TestNGUserLastName");
        user.setDescription("Description");
        user.setEmail("testNGUserName@gmail.com");
        Set<UserProfile> set = new HashSet<>();
        set.add(userProfileDao.findAll().get(2));
        user.setUserProfiles(set);
        userDao.save(user);

        User userVer = userDao.findBySSO("TestNGUserSSOID");
        Assert.assertNotNull(userVer);
    }

    @Test(priority = 20)
    public void testFindBySSO() throws Exception {
        User user = userDao.findBySSO("TestNGUserSSOID");
        Assert.assertNotNull(user);
    }

    @Test(priority = 30)
    public void testFindAllUsers() throws Exception {
        List<User> users = userDao.findAllUsers();
        Assert.assertTrue(users.size() > 0);
    }

    @Test(priority = 40)
    public void testFindById() throws Exception {
        User user = userDao.findBySSO("TestNGUserSSOID");
        Assert.assertNotNull(userDao.findById(user.getId()));
    }

    @Test(priority = 50)
    public void testDeleteBySSO() throws Exception {
        userDao.deleteBySSO("TestNGUserSSOID");
        User user = userDao.findBySSO("TestNGUserSSOID");
        Assert.assertNull(user);
    }

}