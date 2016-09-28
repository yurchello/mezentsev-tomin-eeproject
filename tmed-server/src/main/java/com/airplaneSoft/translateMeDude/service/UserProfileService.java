package com.airplaneSoft.translateMeDude.service;



import com.airplaneSoft.translateMeDude.model.UserProfile;

import java.util.List;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
