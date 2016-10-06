package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

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
}
