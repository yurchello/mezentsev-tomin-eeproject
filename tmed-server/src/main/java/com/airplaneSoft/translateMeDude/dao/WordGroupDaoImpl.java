package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Repository("wordGroupDao")
public class WordGroupDaoImpl extends AbstractDao<Integer, WordsGroup> implements WordGroupDao{

    @Override
    public void createGroup(WordsGroup wordsGroup, User user) {
        wordsGroup.setUser(user);
        persist(wordsGroup);
    }

    @Override
    public void updateGroup(WordsGroup wordsGroup) {
        update(wordsGroup);
    }

    @Override
    public void deleteGroup(WordsGroup wordsGroup) {
        delete(wordsGroup);
    }

    @Override
    public List<WordsGroup> findAllUserGroups(User user) {
        Query query = getSession().createQuery("FROM WordsGroup as worgGoup where worgGoup.user =:currentUser");
        query.setParameter("currentUser", user);
        List<WordsGroup> groups = query.list();
        if(groups!=null){
            for (WordsGroup wg: groups) {
                Hibernate.initialize(wg.getUser().getUserProfiles());
            }

        }
        return groups;
    }

    @Override
    public WordsGroup findById(Integer id) {
        return getByKey(id);
    }

}
