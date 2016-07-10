package com.wht.system.service;

import java.util.List;

import com.wht.system.model.Function;

public interface SystemService {

	
	public List<Function> getFunctionByType(Long parentID,Integer type);
}
