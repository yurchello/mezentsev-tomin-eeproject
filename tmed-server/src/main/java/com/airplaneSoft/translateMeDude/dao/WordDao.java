package com.airplaneSoft.translateMeDude.dao;


import com.airplaneSoft.translateMeDude.models.vocabulary.Word;

/**
 * Created by Mezentsev.Y on 9/20/2016.
 */
public interface WordDao {

    Word findById(Integer id);

    void deleteWord(Word word);

    void create(Word word);

    void updateWord(Word word);
}
