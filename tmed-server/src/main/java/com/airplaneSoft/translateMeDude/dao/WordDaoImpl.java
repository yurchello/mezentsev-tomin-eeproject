package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import org.springframework.stereotype.Repository;

/**
 * Created by Mezentsev.Y on 9/20/2016.
 */
@Repository("wordDao")
public class WordDaoImpl extends AbstractDao<Integer, Word> implements WordDao {
    @Override
    public Word findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void deleteWord(Word word) {
        delete(word);
    }

    @Override
    public void create(Word word) {
        persist(word);
    }

    @Override
    public void updateWord(Word word) {
        update(word);
    }
}
