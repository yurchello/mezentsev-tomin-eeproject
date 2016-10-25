package com.airplaneSoft.translateMeDude.service;


import com.airplaneSoft.translateMeDude.models.User;

import java.util.List;


public interface UserService {
	
	User findById(int id);

	User findBySSO(String ssoId);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);

}