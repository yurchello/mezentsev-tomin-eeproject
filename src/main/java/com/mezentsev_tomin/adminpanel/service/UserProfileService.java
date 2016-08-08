package com.mezentsev_tomin.adminpanel.service;



import com.mezentsev_tomin.adminpanel.model.UserProfile;

import java.util.List;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
