package com.mezentsev_tomin.adminpanel.model.vocabulary;

import java.io.Serializable;

/**
 * Created by Mezentsev.Y on 8/31/2016.
 */
public class Word implements Serializable {
    private Integer id;
    private String englishWord;
    private String translation;
    private String transcription;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Word[" +
                "id=" + id +
                ", englishWord=" + englishWord +
                ", translation=" + translation  +
                ", transcription=" + transcription  +
                ", description=" + description  +
                ']';
    }
}
