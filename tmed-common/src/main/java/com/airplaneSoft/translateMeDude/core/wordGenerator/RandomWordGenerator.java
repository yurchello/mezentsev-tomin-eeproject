package com.airplaneSoft.translateMeDude.core.wordGenerator;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import java.util.*;

/**
 * Provide get the word by random order
 */
public class RandomWordGenerator implements WordGenerator {
    private List<Word> words;

    public RandomWordGenerator(List<Word> words) {
        this.words = words;
    }

    /**
     *
     * @return random Word entity from collection
     */
    @Override
    public Word getWord() {
        if (words == null){
            System.out.println("Words set is null.");
            return null;
        }
        Random rnd = new Random();
        int i = rnd.nextInt(words.size());
        return words.get(i);
    }
}
