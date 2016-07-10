package com.wht.core;

import java.io.Serializable;

public interface BaseService {

	
	public <T> T addObject(T Obj);
	
	public <T> T getObject(Class<T> clazz,Serializable id);
	
	public void  updateObject(Object object);
	
	public void  deleteObject(Object object);
	
	public void  deleteById(Class clazz,Serializable id);
	
}
