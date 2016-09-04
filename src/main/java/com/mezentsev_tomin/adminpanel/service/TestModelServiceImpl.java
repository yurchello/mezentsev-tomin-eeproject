package com.mezentsev_tomin.adminpanel.service;

import com.mezentsev_tomin.adminpanel.dao.TestModelDao;
import com.mezentsev_tomin.adminpanel.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Service("testModelService")
@Transactional
public class TestModelServiceImpl implements TestModelService {

    @Autowired
    TestModelDao dao;

    @Override
    public List<TestModel> findAllTestModels() {
        return dao.findAllTestModels();
    }

    @Override
    public TestModel findById(int id) {
        return dao.findById(id);
    }
}
