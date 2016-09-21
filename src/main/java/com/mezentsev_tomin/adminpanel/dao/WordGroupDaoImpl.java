package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.model.User;
import com.mezentsev_tomin.adminpanel.model.vocabulary.Word;
import com.mezentsev_tomin.adminpanel.model.vocabulary.WordsGroup;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Repository("wordGroupDao")
public class WordGroupDaoImpl extends AbstractDao<Integer, WordsGroup> implements WordGroupDao{
    @Autowired
    private SessionFactory sessionFactory;

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
//        Session session = getSession();
//        session.save(wordsGroup);
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
        Session session = getSession();
        //Query query = session.createQuery("SELECT group.id FROM Developer D");
        //List groups = query.list();
        return null;
    }

    @Override
    public WordsGroup findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void addWord(WordsGroup wordsGroup, Word word) {

    }


    @Override
    public void updateWord(WordsGroup wordsGroup, Word word) {

    }

    @Override
    public void findAllWords(WordsGroup wordsGroup) {

    }
}
