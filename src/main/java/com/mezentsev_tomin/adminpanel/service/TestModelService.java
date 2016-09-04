package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.model.TestModel;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */
public interface TestModelService {
    List<TestModel> findAllTestModels();
    TestModel findById(int id);
}
