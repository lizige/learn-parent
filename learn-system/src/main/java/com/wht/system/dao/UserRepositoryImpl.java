package com.wht.system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wht.system.model.User;

public class UserRepositoryImpl implements UserDAO {


	@PersistenceContext
	private EntityManager  entityManager;

	public User getUserByLoginName(String loginName) {
		
		User user = null;
		
		String hql = " from User where loginName=:loginName ";
	
		List<User> users =  entityManager.createQuery(hql).setParameter("loginName", loginName).getResultList();
		
		if(users.size()>0) user = users.get(0);
		
		return user;
	}


	public User query(String loginName) {

	    return this.getUserByLoginName(loginName);
	}



}
