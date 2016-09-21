package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.dao.WordDao;
import com.mezentsev_tomin.adminpanel.dao.WordGroupDao;
import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Service("wordGroupService")
@Transactional
public class WordGroupServiceImpl implements WordGroupService {

    @Autowired
    WordGroupDao dao;
    @Autowired
    WordDao wordDao;



    @Override
    public void createGroup(WordsGroup wordsGroup, User user) {
        dao.createGroup(wordsGroup,user);
    }

    @Override
    public void updateGroup(WordsGroup wordsGroup) {
        dao.updateGroup(wordsGroup);
    }

    @Override
    public void deleteGroup(WordsGroup wordsGroup) {
        dao.deleteGroup(wordsGroup);
        Set<Word> words = wordsGroup.getWords();
        for (Word word:words) {
            wordDao.deleteWord(word);
        }
    }

    @Override
    public void addWordToGroup(Word word, WordsGroup wordsGroup) {
        wordDao.create(word);
        wordsGroup.getWords().add(word);
        dao.updateGroup(wordsGroup);
    }

    @Override
    public void removeWordFromGroup(Word word, WordsGroup wordsGroup) {
        wordsGroup.getWords().remove(word);
        dao.updateGroup(wordsGroup);
        wordDao.deleteWord(word);
    }

    @Override
    public List<WordsGroup> findAllUserGroups(User user) {
        return dao.findAllUserGroups(user);
    }

    @Override
    public List<WordsGroup> findAllGroups() {
        return dao.findAllGroups();
    }

    @Override
    public WordsGroup findById(Integer id) {
        return dao.findById(id);
    }
}
