package com.airplaneSoft.translateMeDude.winApp.viewModel;

import com.airplaneSoft.translateMeDude.core.translationVerefier.TranslationVerifier;

/**
 * Created by Mezentsev.Y on 10/27/2016.
 */
public class ForeignNativeMainViewModel extends MainViewModel{
    private TranslationVerifier translationVerifier;

    public ForeignNativeMainViewModel(TranslationVerifier translationVerifier) {
        this.translationVerifier = translationVerifier;
    }

    @Override
    public void init(){
        wordProperty.setValue(this.wordEntity.getWord());
        correctTranslationProperty.setValue(this.wordEntity.getTranslation());
        //translation = this.wordEntity.getTranslation();
        descriptionProperty.setValue(this.wordEntity.getDescription());
        transcriptionProperty.setValue(this.wordEntity.getTranscription());
    }

    @Override
    public boolean isCorrectTranslate() {
        return translationVerifier.verify(translationProperty.get(), correctTranslationProperty.get());
    }
}
