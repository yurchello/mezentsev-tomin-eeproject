package com.mezentsev_tomin.adminpanel.model.vocabulary;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 8/31/2016.
 */
public class Group implements Serializable {
    private Integer id;
    private String name;
    private Set<Word> words = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Group[" +
                "id=" + id +
                ", name=" + name +
                ", words=" + words +
                ']';
    }
}
