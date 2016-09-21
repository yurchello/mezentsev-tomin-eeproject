package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;

/**
 * Created by Mezentsev.Y on 9/20/2016.
 */
public interface WordDao {
    Word findById(Integer id);
    void deleteWord(Word word);
    void create(Word word);
    void updateWord(Word word);
}
