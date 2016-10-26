package com.airplaneSoft.translateMeDude.core.wordManager;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;

import java.util.List;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 10/26/2016.
 */
public interface WordsManager {
    List<Word> getWords();
    List<Word> getWords(String... groupNames);
}
