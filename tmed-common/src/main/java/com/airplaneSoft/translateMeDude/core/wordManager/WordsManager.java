package com.airplaneSoft.translateMeDude.core.wordManager;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;

import java.util.List;
import java.util.Set;

/**
 * Provide to get list of the word entities
 */
public interface WordsManager {
    /**
     *
     * @return all words list
     */
    List<Word> getWords();

    /**
     *
     * @param groupNames the names of the WordGroup to filtered
     * @return
     */
    List<Word> getWords(String... groupNames);
}
