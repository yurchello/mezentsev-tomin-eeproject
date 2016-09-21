package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */
public interface WordGroupDao {
    List<WordsGroup> findAllGroups();//todo remove

    void createGroup(WordsGroup wordsGroup, User user);
    void updateGroup(WordsGroup wordsGroup);
    void deleteGroup(WordsGroup wordsGroup);
    List<WordsGroup> findAllUserGroups(User user);
    WordsGroup findById(Integer id);

    void addWord(WordsGroup wordsGroup, Word word);
    void updateWord(WordsGroup wordsGroup, Word word);
    void findAllWords(WordsGroup wordsGroup);

}
