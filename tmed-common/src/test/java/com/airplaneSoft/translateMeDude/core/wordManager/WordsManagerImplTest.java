package com.airplaneSoft.translateMeDude.core.wordManager;

import com.airplaneSoft.translateMeDude.core.WordsMock;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/2/2016.
 */
public class WordsManagerImplTest {

    private WordsManager wordsManager;
    private List<WordsGroup> wordsGroupList;

    @BeforeClass
    public void init(){
        this.wordsGroupList = new WordsMock().getWordsGroupList();
        this.wordsManager = new WordsManagerImpl(wordsGroupList);
    }

    @Test
    public void testGetWords() throws Exception {
        List<Word> words = this.wordsManager.getWords();
        Assert.assertEquals(words.size(), 30);
    }

    /**
     * Not implemented yet
     */
    @Test(enabled=false)
    public void testGetWords1() throws Exception {
        List<Word> words = this.wordsManager.getWords("Test group Name1","Test group Name2");
        Assert.assertEquals(words.size(), 20);
    }

}