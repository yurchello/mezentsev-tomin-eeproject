package com.mezentsev_tomin.adminpanel.dao;

import com.mezentsev_tomin.adminpanel.model.TestModel;


import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */
public interface TestModelDao {
    List<TestModel> findAllTestModels();
    TestModel findById(int id);

}
