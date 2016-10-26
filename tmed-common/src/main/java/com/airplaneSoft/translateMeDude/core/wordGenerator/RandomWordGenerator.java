package com.airplaneSoft.translateMeDude.core.wordGenerator;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;

import java.util.*;

/**
 * Created by Mezentsev.Y on 10/26/2016.
 */
public class RandomWordGenerator implements WordGenerator {
    private List<Word> words;


    public RandomWordGenerator(List<Word> words) {
        this.words = words;
    }

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
