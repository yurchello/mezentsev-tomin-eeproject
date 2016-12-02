package com.airplaneSoft.translateMeDude.winApp.utils;

import com.airplaneSoft.translateMeDude.core.wordGenerator.RandomWordGenerator;
import com.airplaneSoft.translateMeDude.core.wordGenerator.WordGenerator;
import com.airplaneSoft.translateMeDude.core.wordManager.WordsManager;
import com.airplaneSoft.translateMeDude.core.wordManager.WordsManagerImpl;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsImpl;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtilsImpl;

/**
 * This class provide keep vocabulary in memory
 */
public class VocabularyHolder {
    private static VocabularyHolder instance;
    private WordsManager wordsManager;
    private WordGenerator wordGenerator;
    private VocabularyHolder() {
        VocabularyUtils vocabularyUtils = new VocabularyUtilsImpl(SettingsImpl.getInstance().getVocabularyPath());
        this.wordsManager = new WordsManagerImpl(vocabularyUtils.getFullVocabulary());
        this.wordGenerator = new RandomWordGenerator(this.wordsManager.getWords());

    }

    public  static VocabularyHolder getInstance(){
        if (instance == null){
            instance = new VocabularyHolder();
            return instance;
        }
        return instance;
    }

    public WordsManager getWordsManager() {
        return wordsManager;
    }

    public WordGenerator getWordGenerator() {
        return wordGenerator;
    }
}
