package com.airplaneSoft.translateMeDude.service;

import com.airplaneSoft.translateMeDude.configuration.HibernateTestConfiguration;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/6/2016.
 */
@ContextConfiguration(classes = {HibernateTestConfiguration.class})
public class UserProfileServiceImplTest  extends AbstractTestNGSpringContextTests {
    @Autowired
    UserProfileService userProfileService;

    @Test
    public void testFindById() throws Exception {
        UserProfile userProfile = userProfileService.findById(1);
        Assert.assertEquals(userProfile.getType(), "USER");
    }

    @Test
    public void testFindByType() throws Exception {
        UserProfile userProfile = userProfileService.findByType("USER");
        Assert.assertEquals(userProfile.getType(), "USER");
    }

    @Test
    public void testFindAll() throws Exception {
        List<UserProfile> userProfileList = userProfileService.findAll();
        Assert.assertEquals(userProfileList.size(), 3);
    }

}