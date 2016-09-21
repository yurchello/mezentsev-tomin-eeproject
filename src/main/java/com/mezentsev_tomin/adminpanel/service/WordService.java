package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mezentsev.Y on 9/21/2016.
 */

public interface WordService {
    Word findById(Integer id);
    void delete(Integer id);
    void create(Word word);
    void update(Word word);
}
