package com.wht.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wht.system.dao.FunctionRepository;
import com.wht.system.model.Function;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private FunctionRepository functionRepository;
	
	public List<Function> getFunctionByType(Long parentID, Integer type) {
		
		return this.functionRepository.findByParentIdOrderByOrderNo(parentID, type);
	}



}
