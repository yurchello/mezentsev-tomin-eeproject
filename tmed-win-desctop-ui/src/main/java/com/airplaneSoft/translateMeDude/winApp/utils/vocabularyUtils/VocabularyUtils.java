package com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.models.settings.SettingsImpl;

import java.nio.file.Path;
import java.util.List;

/**
 * This interface describes signature of vocabulary object saving
 */
public interface VocabularyUtils {

    /**
     * Serialize  wordsGroupList to hard drive.
     * @param wordsGroupList
     * @return true if operation is success
     */
    boolean saveFullVocabulary(List<WordsGroup> wordsGroupList);

    List<WordsGroup> getFullVocabulary();

}
