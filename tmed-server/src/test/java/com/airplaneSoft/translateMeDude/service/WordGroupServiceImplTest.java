package com.airplaneSoft.translateMeDude.service;

import com.airplaneSoft.translateMeDude.configuration.HibernateTestConfiguration;
import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.UserProfile;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/6/2016.
 */
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(classes = {HibernateTestConfiguration.class})
public class WordGroupServiceImplTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;
    User user;
    @Autowired
    WordGroupService wordGroupService;


    @BeforeClass
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
            set.add(userProfileService.findAll().get(2));
            user.setUserProfiles(set);
            userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user == null) user = userService.findBySSO("TestNGUserSSOID");
    }


    @Test(priority = 10)
    public void testCreateGroup() throws Exception {
        WordsGroup wordsGroup1 = new WordsGroup();
        wordsGroup1.setName("TestNGUserGroup1");
        WordsGroup wordsGroup2 = new WordsGroup();
        wordsGroup2.setName("TestNGUserGroup2");
        wordGroupService.createGroup(wordsGroup1, user);
        wordGroupService.createGroup(wordsGroup2, user);
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        Assert.assertTrue(wordsGroups.contains(wordsGroup1));
    }

    @Test(priority = 20)
    public void testAddWordToGroup() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        WordsGroup wordsGroup = wordsGroups.get(0);
        Integer id = wordsGroup.getId();
        Word word = new Word();
        word.setWord("word");
        word.setDescription("desc");
        word.setTranslation("trans");
        wordGroupService.addWordToGroup(word, wordsGroup);
        Assert.assertEquals(new ArrayList<>(wordGroupService.findById(id).getWords()).get(0).getWord(),(word.getWord()));
    }

    @Test(priority = 30)
    public void testRemoveWordFromGroup() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        WordsGroup wordsGroup = wordsGroups.get(0);
        Integer id = wordsGroup.getId();
        Word word = new ArrayList<>(wordsGroup.getWords()).get(0);
        wordGroupService.removeWordFromGroup(word,wordsGroup);
        Assert.assertEquals(wordGroupService.findById(id).getWords().size(), 0);
    }

    @Test(priority = 40)
    public void testFindAllUserGroups() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        Assert.assertEquals(wordsGroups.size(), 2);
    }

    @Test(priority = 50)
    public void testFindById() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        WordsGroup wordsGroup = wordsGroups.get(0);
        Integer id = wordsGroup.getId();
        Assert.assertNotNull(wordGroupService.findById(id));
    }

    @Test(priority = 60)
    public void testUpdateGroup() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        WordsGroup wordsGroup = wordsGroups.get(0);
        wordsGroup.setName("TestNGUserSSOIDRenamed");
        Integer id = wordsGroup.getId();
        wordGroupService.updateGroup(wordsGroup);
        Assert.assertEquals(wordGroupService.findById(id).getName(), "TestNGUserSSOIDRenamed");
    }

    @Test(priority = 70)
    public void testDeleteGroup() throws Exception {
        List<WordsGroup> wordsGroups = wordGroupService.findAllUserGroups(user);
        WordsGroup wordsGroup = wordsGroups.get(1);
        Integer id= wordsGroup.getId();
        wordGroupService.deleteGroup(wordsGroup);
        Assert.assertNull(wordGroupService.findById(id));
    }

    @AfterClass
    public void cleanUp(){
        userService.deleteUserBySSO(user.getSsoId());
    }
}