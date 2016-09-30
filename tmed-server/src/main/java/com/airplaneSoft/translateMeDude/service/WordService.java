package com.airplaneSoft.translateMeDude.service;

import com.airplaneSoft.translateMeDude.models.vocabulary.Word;

/**
 * Created by Mezentsev.Y on 9/21/2016.
 */

public interface WordService {
    Word findById(Integer id);
    void delete(Integer id);
    void create(Word word);
    void update(Word word);
}
