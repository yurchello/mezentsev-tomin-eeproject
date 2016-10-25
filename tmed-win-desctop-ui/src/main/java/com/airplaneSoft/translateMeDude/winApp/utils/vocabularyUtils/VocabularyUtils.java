package com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.settings.settingsModel.SettingsImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Mezentsev.Y on 10/24/2016.
 */
public interface VocabularyUtils {
    String VOCABULARY_FILE_NAME = "vocabulary.vcb";
    Path VOCABULARY_FILE_PATH = SettingsImpl.getAppPath().resolve(VOCABULARY_FILE_NAME);

    boolean saveFullVocabulary(List<WordsGroup> wordsGroupList);
    List<WordsGroup> getFullVocabulary();

}
