package com.mezentsev_tomin.adminpanel.dao;




import com.mezentsev_tomin.adminpanel.model.UserProfile;

import java.util.List;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
