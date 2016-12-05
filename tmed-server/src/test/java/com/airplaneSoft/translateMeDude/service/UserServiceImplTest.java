package com.airplaneSoft.translateMeDude.service;

import com.airplaneSoft.translateMeDude.configuration.HibernateTestConfiguration;
import com.airplaneSoft.translateMeDude.dao.*;
import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 12/5/2016.
 */

@ContextConfiguration(classes = {HibernateTestConfiguration.class})
public class UserServiceImplTest extends AbstractTestNGSpringContextTests{

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    UserProfileService userProfileService;

    @Test(priority = 10)
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setSsoId("TestNGUserSSOID");
        user.setPassword("TestNGUserPassword");
        user.setFirstName("TestNGUserName");
        user.setLastName("TestNGUserLastName");
        user.setDescription("Description");
        user.setEmail("testNGUserName@gmail.com");
        Set<UserProfile> set = new HashSet<>();
        set.add(userProfileService.findAll().get(2));
        user.setUserProfiles(set);
        userService.saveUser(user);

        User userVer = userService.findBySSO("TestNGUserSSOID");
        Assert.assertNotNull(userVer);
    }

    @Test(priority = 20)
    public void testFindById() throws Exception {
        User user = userService.findBySSO("TestNGUserSSOID");
        Assert.assertEquals(userService.findById(user.getId()), user);
    }

    @Test(priority = 30)
    public void testFindBySSO() throws Exception {
        User user = userService.findBySSO("TestNGUserSSOID");
        Assert.assertNotNull(user);
    }

    @Test(priority = 40)
    public void testUpdateUser() throws Exception {
        User user = userService.findBySSO("TestNGUserSSOID");
        user.setFirstName("TestNGUserFirstRenamed");
        userService.updateUser(user);
        User userVer = userService.findBySSO("TestNGUserSSOID");
        Assert.assertEquals(userVer.getFirstName(),"TestNGUserFirstRenamed");
    }

    @Test(priority = 50)
    public void testFindAllUsers() throws Exception {
        List<User> userList = userService.findAllUsers();
        Assert.assertTrue(userList.size() > 0);
    }

    @Test(priority = 60)
    public void testIsUserSSOUnique() throws Exception {
        User user = userService.findBySSO("TestNGUserSSOID");
        if (user == null) Assert.fail("User is null");
        Assert.assertFalse(userService.isUserSSOUnique(user.getId(), user.getSsoId()));
    }

    @Test(priority = 70)
    public void testDeleteUserBySSO() throws Exception {
        userService.deleteUserBySSO("TestNGUserSSOID");
        User user = userService.findBySSO("TestNGUserSSOID");
        Assert.assertNull(user);
    }
}