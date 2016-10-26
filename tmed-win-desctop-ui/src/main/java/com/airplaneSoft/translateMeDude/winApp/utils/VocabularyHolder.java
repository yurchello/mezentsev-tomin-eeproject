package com.airplaneSoft.translateMeDude.winApp.utils;

import com.airplaneSoft.translateMeDude.core.wordGenerator.RandomWordGenerator;
import com.airplaneSoft.translateMeDude.core.wordGenerator.WordGenerator;
import com.airplaneSoft.translateMeDude.core.wordManager.WordsManager;
import com.airplaneSoft.translateMeDude.core.wordManager.WordsManagerImpl;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtilsImpl;

/**
 * Created by Mezentsev.Y on 10/26/2016.
 */
public class VocabularyHolder {
    private static VocabularyHolder vocabularyHolder;
    private WordsManager wordsManager;
    private WordGenerator wordGenerator;
    private VocabularyHolder() {
        VocabularyUtils vocabularyUtils = new VocabularyUtilsImpl();
        this.wordsManager = new WordsManagerImpl(vocabularyUtils.getFullVocabulary());
        this.wordGenerator = new RandomWordGenerator(this.wordsManager.getWords());

    }

    public  static VocabularyHolder getInstance(){
        if (vocabularyHolder == null){
            return new VocabularyHolder();
        }else return vocabularyHolder;
    }

    public WordsManager getWordsManager() {
        return wordsManager;
    }

    public WordGenerator getWordGenerator() {
        return wordGenerator;
    }
}
