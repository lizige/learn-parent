package com.wht.system.service;

import java.util.List;

import org.springframework.security.access.ConfigAttribute;

import com.wht.system.model.UrlPermission;

public interface SecurityManager {

	
	public List<UrlPermission> getAllConfigAttributes();
}
