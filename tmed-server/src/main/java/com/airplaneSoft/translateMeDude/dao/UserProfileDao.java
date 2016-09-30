package com.airplaneSoft.translateMeDude.dao;




import com.airplaneSoft.translateMeDude.models.UserProfile;

import java.util.List;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
