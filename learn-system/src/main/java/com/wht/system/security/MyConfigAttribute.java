package com.wht.system.security;

import org.springframework.security.access.ConfigAttribute;

public class MyConfigAttribute implements ConfigAttribute {

	private String requestUrl;
	
	
	public MyConfigAttribute(String requestUrl) {
		
		this.requestUrl = requestUrl;
	}

	@Override
	public String getAttribute() {
		
		return this.requestUrl;
	}

}
