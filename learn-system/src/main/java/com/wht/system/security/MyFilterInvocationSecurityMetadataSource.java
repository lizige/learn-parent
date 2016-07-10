package com.wht.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.wht.system.model.UrlPermission;
import com.wht.system.service.SecurityManager;


public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	
	public MyFilterInvocationSecurityMetadataSource(SecurityManager securityManager) {
	
        List<UrlPermission> urlPermissions =securityManager.getAllConfigAttributes();
		
		this.requestMap = new HashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		for(UrlPermission urlPermission : urlPermissions) {
			
			RequestMatcher requestMatcher = new AntPathRequestMatcher(urlPermission.getUrl());
			
			if(this.requestMap.containsKey(requestMatcher)) {
				
				 this.requestMap.get(requestMatcher).add(urlPermission);
			}else {
				
				Collection<ConfigAttribute> myAttributes = new ArrayList<ConfigAttribute>();
				myAttributes.add(urlPermission);
				
				this.requestMap.put(requestMatcher, myAttributes);
			}
			
		}
	}
	
	

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }


	public boolean supports(Class<?> clazz) {
		
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
