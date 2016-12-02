package com.airplaneSoft.translateMeDude.core;

import com.airplaneSoft.translateMeDude.core.wordManager.WordsManagerImpl;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezentsev.Y on 12/2/2016.
 */
public class WordsMock {
    private List<WordsGroup> wordsGroupList = new ArrayList<>();
    private List<Word> wordsList = new ArrayList<>();

    public WordsMock() {
       init();
    }

    private void init(){
        for (int j = 1; j<=3; j++){
            WordsGroup wordsGroup = new WordsGroup();
            wordsGroup.setName("Test group Name" + j);
            for (int i = 1; i<=10; i++){
                Word word = new Word();
                word.setWord( "Test group Name" + j + "some word " + i);
                word.setTranslation( "Test group Name" + j + "translation " + i);
                word.setDescription( "Test group Name" + j + "description " + i);
                wordsGroup.getWords().add(word);
                this.wordsList.add(word);
            }
            wordsGroupList.add(wordsGroup);
        }
    }

    public List<WordsGroup> getWordsGroupList() {
        return wordsGroupList;
    }

    public List<Word> getWordsList() {
        return wordsList;
    }
}
