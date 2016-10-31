package com.airplaneSoft.translateMeDude.dao;

import com.airplaneSoft.translateMeDude.models.User;
import com.airplaneSoft.translateMeDude.models.vocabulary.Word;
import com.airplaneSoft.translateMeDude.models.vocabulary.WordsGroup;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Repository("wordGroupDao")
public class WordGroupDaoImpl extends AbstractDao<Integer, WordsGroup> implements WordGroupDao{

    @SuppressWarnings("unchecked")
    public List<WordsGroup> findAllGroups(){
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        Session session = getSession();
        session.get(WordsGroup.class, new Integer(1));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<WordsGroup> groups = (List<WordsGroup>) criteria.list();
        return groups;
    }

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
        List groups = query.list();
        return groups;
    }

    @Override
    public WordsGroup findById(Integer id) {
        return getByKey(id);
    }

}
