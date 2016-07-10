package com.wht.system.dao;

import com.wht.system.model.User;

public interface UserDAO {

	
	public User getUserByLoginName(String loginName);
	
	public User query(String loginName);
}
