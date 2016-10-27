package com.airplaneSoft.translateMeDude.core.wordManager;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Mezentsev.Y on 10/26/2016.
 */
public class WordsManagerImpl implements WordsManager {

    private List<WordsGroup> wordsGroupList;

    public WordsManagerImpl(List<WordsGroup> wordsGroupList) {
        this.wordsGroupList = wordsGroupList;
    }

    @Override
    public List<Word> getWords() {
        if (wordsGroupList == null){
            System.out.println("wordsGroupList is null");
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
