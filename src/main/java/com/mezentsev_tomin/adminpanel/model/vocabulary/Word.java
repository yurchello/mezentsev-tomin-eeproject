package com.mezentsev_tomin.adminpanel.model.vocabulary;

import com.mezentsev_tomin.adminpanel.model.User;
import org.hibernate.validator.constraints.NotEmpty;

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

    @NotEmpty
    @Column(name="WORD", nullable=false)
    private String word;
    @NotEmpty
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Word)) return false;
        Word other = (Word) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id))
            return false;
        if (word == null) {
            if (other.word != null) return false;
        } else if (!word.equals(other.word)) return false;
        return true;
    }
}
