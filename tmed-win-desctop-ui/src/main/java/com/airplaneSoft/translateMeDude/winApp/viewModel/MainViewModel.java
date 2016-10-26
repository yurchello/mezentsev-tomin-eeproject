package com.airplaneSoft.translateMeDude.winApp.viewModel;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.winApp.utils.VocabularyHolder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Mezentsev.Y on 10/24/2016.
 */
public abstract class MainViewModel {
    protected StringProperty wordProperty = new SimpleStringProperty(this,"wordProperty");
    protected StringProperty translationProperty = new SimpleStringProperty(this,"translationProperty");
    protected StringProperty correctTranslationProperty = new SimpleStringProperty(this,"correctTranslationProperty");
    protected StringProperty descriptionProperty = new SimpleStringProperty(this,"descriptionProperty");;
    protected StringProperty transcriptionProperty = new SimpleStringProperty(this,"transcriptionProperty");
    //private boolean getCorrectTranslationProperty;
    protected Word wordEntity;

    public MainViewModel() {
        this.wordEntity = VocabularyHolder.getInstance().getWordGenerator().getWord();
        init();
    }

    public abstract void init();

    public boolean getCorrectTranslationProperty(){
        //todo comparsion service
        return false;
    }

    public String getWordProperty() {
        return wordProperty.get();
    }

    public StringProperty wordPropertyProperty() {
        return wordProperty;
    }

    public void setWordProperty(String wordProperty) {
        this.wordProperty.set(wordProperty);
    }

    public String getTranslationProperty() {
        return translationProperty.get();
    }

    public StringProperty translationPropertyProperty() {
        return translationProperty;
    }

    public void setTranslationProperty(String translationProperty) {
        this.translationProperty.set(translationProperty);
    }

    public String getCorrectTranslation() {
        return correctTranslationProperty.get();
    }

    public StringProperty correctTranslationPropertyProperty() {
        return correctTranslationProperty;
    }

    public void setCorrectTranslationProperty(String correctTranslationProperty) {
        this.correctTranslationProperty.set(correctTranslationProperty);
    }

    public String getDescriptionProperty() {
        return descriptionProperty.get();
    }

    public StringProperty descriptionPropertyProperty() {
        return descriptionProperty;
    }

    public void setDescriptionProperty(String descriptionProperty) {
        this.descriptionProperty.set(descriptionProperty);
    }

    public String getTranscriptionProperty() {
        return transcriptionProperty.get();
    }

    public StringProperty transcriptionPropertyProperty() {
        return transcriptionProperty;
    }

    public void setTranscriptionProperty(String transcriptionProperty) {
        this.transcriptionProperty.set(transcriptionProperty);
    }

    public Word getWordEntity() {
        return wordEntity;
    }

    public void setWordEntity(Word wordEntity) {
        this.wordEntity = wordEntity;
    }
}
