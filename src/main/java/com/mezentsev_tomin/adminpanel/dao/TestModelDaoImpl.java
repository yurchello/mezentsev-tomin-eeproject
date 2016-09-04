package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.model.TestModel;
import com.mezentsev_tomin.adminpanel.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Repository("testModelDao")
public class TestModelDaoImpl extends AbstractDao<Integer, TestModel> implements TestModelDao{

    @Override
    public List<TestModel> findAllTestModels() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<TestModel> testModels = (List<TestModel>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
        return testModels;
    }

    public TestModel findById(int id) {
        TestModel testModel = getByKey(id);
        return testModel;
    }
}
