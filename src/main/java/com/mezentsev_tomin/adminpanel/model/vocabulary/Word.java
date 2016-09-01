package com.mezentsev_tomin.adminpanel.model.vocabulary;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mezentsev.Y on 8/31/2016.
 */
@Entity
@Table(name="WORD")
public class Word implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="WORD", nullable=false)
    private String word;
    @Column(name="TRANSLATION", nullable=false)
    private String translation;
    @Column(name="TRANSCRIPTION")
    private String transcription;
    @Column(name="DESCRIPTION")
    private String description;

    public Word() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
                ", word=" + word +
                ", translation=" + translation  +
                ", transcription=" + transcription  +
                ", description=" + description  +
                ']';
    }
}
