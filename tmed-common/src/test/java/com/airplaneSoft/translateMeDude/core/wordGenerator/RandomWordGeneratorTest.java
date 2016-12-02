package com.airplaneSoft.translateMeDude.core.wordGenerator;

import com.airplaneSoft.translateMeDude.core.WordsMock;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mezentsev.Y on 12/2/2016.
 */
public class RandomWordGeneratorTest {
    private List<Word> words = new WordsMock().getWordsList();
    @Test
    public void testGetWord() throws Exception {
        RandomWordGenerator wordGenerator = new RandomWordGenerator(words);
        Word word = wordGenerator.getWord();
        Assert.assertNotNull(word);
    }
}