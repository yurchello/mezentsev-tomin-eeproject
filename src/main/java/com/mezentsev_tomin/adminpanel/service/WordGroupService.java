package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */
public interface WordGroupService{
    List<WordsGroup> findAllGroups();
    void createGroup(String name, User user);
}
