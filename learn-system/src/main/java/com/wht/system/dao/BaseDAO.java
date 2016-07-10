package com.wht.system.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wht.system.model.User;


public class BaseDAO {

	public BaseDAO() {
		
       List<?> data = new ArrayList<User>();		
		
	}
	
	public <T> T getEntityByID(Class<T> t,Serializable id) {
		
		return (T)null;
	}

}
