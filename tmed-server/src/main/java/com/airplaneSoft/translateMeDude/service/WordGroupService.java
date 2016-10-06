package com.airplaneSoft.translateMeDude.service;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */
public interface WordGroupService{
    void createGroup(WordsGroup wordsGroup, User user);
    void updateGroup(WordsGroup wordsGroup);
    void deleteGroup(WordsGroup wordsGroup);
    void addWordToGroup(Word word, WordsGroup wordsGroup);
    void removeWordFromGroup(Word word, WordsGroup wordsGroup);
    List<WordsGroup> findAllUserGroups(User user);
    WordsGroup findById(Integer id);
}
