package com.airplaneSoft.translateMeDude.service;

import com.airplaneSoft.translateMeDude.dao.WordDao;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mezentsev.Y on 9/21/2016.
 */
@Service("wordService")
@Transactional
public class WordServiceImpl implements WordService{
    @Autowired
    WordDao wordDao;

    @Override
    public Word findById(Integer id) {
        return wordDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
        Word word = wordDao.findById(id);
        wordDao.deleteWord(word);
    }

    @Override
    public void create(Word word) {
        wordDao.create(word);
    }

    @Override
    public void update(Word word) {
        wordDao.updateWord(word);
    }
}
