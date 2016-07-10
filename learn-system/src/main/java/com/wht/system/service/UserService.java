package com.wht.system.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wht.system.model.User;

public interface UserService extends UserDetailsService{

	
	public List<User> listAllUsers();
	
	
	public User createUser(User user) ;
	
	public void updateUser(User user);
	
	public void deleteUserById(Long id);
	
 	public User getUserById(Long id);

	public User getUserByLoginName(String  loginName);
	
	
}
