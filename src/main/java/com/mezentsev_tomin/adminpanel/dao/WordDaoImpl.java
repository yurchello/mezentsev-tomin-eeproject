package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import org.hibernate.Session;
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
