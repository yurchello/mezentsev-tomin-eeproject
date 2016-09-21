package com.mezentsev_tomin.adminpanel.model.vocabulary;

import com.mezentsev_tomin.adminpanel.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mezentsev.Y on 9/1/2016.
 */
@Entity
@Table(name="GROUPS")
public class WordsGroup implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="word_group", nullable=false)
    private String name;

//    @Column(name="user_id", nullable=false)
//    private String userId;
    @ManyToOne
    private User user;

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "groups_word",
            joinColumns = { @JoinColumn(name = "GROUP_ID") },
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
