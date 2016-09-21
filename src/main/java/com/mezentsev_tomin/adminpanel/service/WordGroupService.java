package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */
public interface WordGroupService{
    List<WordsGroup> findAllGroups();
    void createGroup(WordsGroup wordsGroup, User user);
    void updateGroup(WordsGroup wordsGroup);
    void deleteGroup(WordsGroup wordsGroup);
    void addWordToGroup(Word word, WordsGroup wordsGroup);
    void removeWordFromGroup(Word word, WordsGroup wordsGroup);
    List<WordsGroup> findAllUserGroups(User user);
    WordsGroup findById(Integer id);
}
