package com.mezentsev_tomin.adminpanel.model.vocabulary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 9/1/2016.
 */
@Entity
@Table(name="W_GROUP")
public class WordsGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="word_group", nullable=false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "w_group_word",
            joinColumns = { @JoinColumn(name = "W_GROUP_ID") },
            inverseJoinColumns = { @JoinColumn(name = "WORD_ID") })
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
}
