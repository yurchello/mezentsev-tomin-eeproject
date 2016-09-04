package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.dao.WordGroupDao;
import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Service("wordGroupService")
@Transactional
public class WordGroupServiceImpl implements WordGroupService {

    @Autowired
    WordGroupDao dao;

    @Override
    public List<WordsGroup> findAllGroups() {
        return dao.findAllGroups();
    }

    @Override
    public void createGroup(String name, User user) {
        dao.createGroup(name,user);
    }
}
