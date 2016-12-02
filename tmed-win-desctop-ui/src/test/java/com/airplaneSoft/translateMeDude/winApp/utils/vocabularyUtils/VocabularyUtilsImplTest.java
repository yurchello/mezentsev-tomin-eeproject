package com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/1/2016.
 */
public class VocabularyUtilsImplTest {
    private String VOCABULARY_FILE_NAME = "vocabulary_test.vcb";
    private Path VOCABULARY_FILE_PATH = SettingsImpl.getAppPath().resolve(VOCABULARY_FILE_NAME);
    private VocabularyUtils vocabularyUtils;
    private  WordsGroup wordsGroup;

    @BeforeClass
    public void init(){
        this.vocabularyUtils = new VocabularyUtilsImpl(VOCABULARY_FILE_PATH);
        this.wordsGroup = new WordsGroup();
        this.wordsGroup.setName("Test group Name");
        for (int i = 1; i<=10; i++){
            Word word = new Word();
            word.setWord("some word " + i);
            word.setTranslation("translation " + i);
            word.setDescription("description " + i);
            wordsGroup.getWords().add(word);
        }
    }

    @Test(priority = 10)
    public void testSaveFullVocabulary() throws Exception {
       boolean result =  vocabularyUtils.saveFullVocabulary(Collections.singletonList(wordsGroup));
       assertTrue(result);
    }

    @Test(priority = 20)
    public void testGetFullVocabulary() throws Exception {
        List<WordsGroup> deserializedWordsGroups = vocabularyUtils.getFullVocabulary();
        assertEquals(deserializedWordsGroups.get(0).getName(), "Test group Name");
        assertEquals(deserializedWordsGroups.get(0).getWords().size(), 10);
    }

}