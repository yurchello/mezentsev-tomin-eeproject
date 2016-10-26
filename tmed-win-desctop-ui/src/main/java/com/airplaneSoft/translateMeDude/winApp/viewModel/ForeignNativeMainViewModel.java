package com.airplaneSoft.translateMeDude.winApp.viewModel;

/**
 * Created by Mezentsev.Y on 10/27/2016.
 */
public class ForeignNativeMainViewModel extends MainViewModel{
    @Override
    public void init(){
        wordProperty.setValue(this.wordEntity.getWord());
        correctTranslationProperty.setValue(this.wordEntity.getTranslation());
        //translation = this.wordEntity.getTranslation();
        descriptionProperty.setValue(this.wordEntity.getDescription());
        transcriptionProperty.setValue(this.wordEntity.getTranscription());
    }
}
