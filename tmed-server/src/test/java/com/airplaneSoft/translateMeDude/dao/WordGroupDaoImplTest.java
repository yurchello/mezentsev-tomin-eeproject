package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/4/2016.
 */
public class WordGroupDaoImplTest extends EntityDaoImplTest{

    @Autowired
    UserDao userDao;
    @Autowired
    UserProfileDao userProfileDao;
    @Autowired
    WordGroupDao wordGroupDao;
    User user;


   @Test(priority = 5)
    public void init(){
        try{
            user = new User();
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
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user == null) user = userDao.findBySSO("TestNGUserSSOID");
    }

    @Test(priority = 65)
    public void clean(){
        userDao.deleteBySSO("TestNGUserSSOID");
        User user = userDao.findBySSO("TestNGUserSSOID");
        Assert.assertNull(user);
    }

    @Test(priority = 10)
    public void testCreateGroup() throws Exception {
        WordsGroup wordsGroup1 = new WordsGroup();
        wordsGroup1.setName("TestNGUserGroup1");
        WordsGroup wordsGroup2 = new WordsGroup();
        wordsGroup2.setName("TestNGUserGroup2");
        User user = userDao.findBySSO("TestNGUserSSOID");
        wordGroupDao.createGroup(wordsGroup1, user);
        wordGroupDao.createGroup(wordsGroup2, user);
        List<WordsGroup> wordsGroups = wordGroupDao.findAllUserGroups(user);
        Assert.assertTrue(wordsGroups.contains(wordsGroup1));
    }

    @Test(priority = 30)
    public void testUpdateGroup() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupDao.findAllUserGroups(user);
        WordsGroup wordsGroup = wordsGroups.get(0);
        wordsGroup.setName("TestNGUserGroup1renamed");
        wordGroupDao.updateGroup(wordsGroup);
        List<WordsGroup> wordsGroupsDB = wordGroupDao.findAllUserGroups(user);
        WordsGroup wg = wordsGroupsDB.get(0);
        Assert.assertEquals(wg.getName(), "TestNGUserGroup1renamed");
    }


    @Test(priority = 40)
    public void testFindAllUserGroups() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupDao.findAllUserGroups(user);
        Assert.assertEquals(wordsGroups.size(),2);
    }

    @Test(priority = 50)
    public void testFindById() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupDao.findAllUserGroups(user);
        Integer id = wordsGroups.get(0).getId();
        WordsGroup wordsGroup = wordGroupDao.findById(id);
        Assert.assertNotNull(wordsGroup);
    }

    @Test(priority = 60)
    public void testDeleteGroup() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupDao.findAllUserGroups(user);
        for (WordsGroup wg: wordsGroups){
            wordGroupDao.deleteGroup(wg);
        }
        List<WordsGroup> wordsGroupsVer = wordGroupDao.findAllUserGroups(user);
        Assert.assertEquals(wordsGroupsVer.size(),0);
        System.out.println();
    }
}