package com.wht.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import com.wht.system.dao.UserRepository;
import com.wht.system.model.UrlPermission;

@Service("securityManager")
public class SecurityManagerImpl implements SecurityManager {

	
	@Resource(name="userRepository")
	private UserRepository userRepository;

	@Override
	public List<UrlPermission> getAllConfigAttributes() {
		
		return this.userRepository.getAllUrlPermission();
	}
	
		

}
