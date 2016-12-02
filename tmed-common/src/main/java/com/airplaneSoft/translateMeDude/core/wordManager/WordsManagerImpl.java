package com.airplaneSoft.translateMeDude.core.wordManager;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordsManagerImpl implements WordsManager {
    private List<WordsGroup> wordsGroupList;

    public WordsManagerImpl(List<WordsGroup> wordsGroupList) {
        this.wordsGroupList = wordsGroupList;
    }

    @Override
    public List<Word> getWords() {
        if (wordsGroupList == null){
            return null;
        }
        Set<Word> wordSet = wordsGroupList.stream().map(WordsGroup::getWords).reduce((a, b) -> {
            a.addAll(b);
            return a;
        }).get();
        return new ArrayList<>(wordSet);
    }

    @Override
    public List<Word> getWords(String... groupNames) {
        //// TODO: 10/26/2016
        return null;
    }
}
