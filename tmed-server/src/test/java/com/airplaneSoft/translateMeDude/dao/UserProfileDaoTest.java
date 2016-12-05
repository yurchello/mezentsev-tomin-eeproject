package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/4/2016.
 */
public class UserProfileDaoTest extends EntityDaoImplTest{
    @Autowired
    UserProfileDao userProfileDao;

    @Test(priority = 10)
    public void testFindAll() throws Exception {
        List<UserProfile> userProfileList = userProfileDao.findAll();
        Assert.assertEquals(userProfileList.size(), 3);
    }

    @Test(priority = 20)
    public void testFindByType() throws Exception {
        UserProfile userProfile = userProfileDao.findByType("USER");
        Assert.assertEquals(userProfile.getType(), "USER");
    }

    @Test(priority = 30)
    public void testFindById() throws Exception {
        UserProfile userProfile = userProfileDao.findById(1);
        Assert.assertEquals(userProfile.getType(), "USER");
    }

}