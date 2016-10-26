package com.airplaneSoft.translateMeDude.winApp.tasks;

import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;
import com.airplaneSoft.translateMeDude.winApp.utils.RemoteServiceUtils;
import com.airplaneSoft.translateMeDude.winApp.utils.vocabularyUtils.VocabularyUtils;
import javafx.concurrent.Task;

import java.util.List;

/**
 * Created by Mezentsev.Y on 10/25/2016.
 */
public class UpdateVocabularyTask extends Task<Void> {
    private RemoteServiceUtils remoteServiceUtils;
    private VocabularyUtils vocabularyUtils;

    public UpdateVocabularyTask(VocabularyUtils vocabularyUtils, RemoteServiceUtils remoteServiceUtils) {
        this.vocabularyUtils = vocabularyUtils;
        this.remoteServiceUtils = remoteServiceUtils;
    }

    @Override
    protected Void call() throws Exception {
        List<WordsGroup> list = remoteServiceUtils.getWordGroupsList();
        vocabularyUtils.saveFullVocabulary(list);
        return null;
    }
}
