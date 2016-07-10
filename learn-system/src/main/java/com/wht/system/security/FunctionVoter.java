package com.wht.system.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.wht.system.model.UrlPermission;


public class FunctionVoter implements AccessDecisionVoter<Object> {

	public FunctionVoter() {
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		if(clazz.isAssignableFrom(MyConfigAttribute.class))
			return true;
		
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		
		boolean isHaveFunc = false;
		
		for(Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();iterator.hasNext();) {
			
			GrantedAuthority grantedAuthority = iterator.next();
			
				
			for(Iterator<ConfigAttribute> iterator2 = attributes.iterator();iterator2.hasNext();) {
				
				ConfigAttribute configAttribute = iterator2.next();
				
				if(configAttribute instanceof UrlPermission) {
					
					UrlPermission urlPermission =(UrlPermission) configAttribute;
					
					if(urlPermission.getRoleCode().equals(grantedAuthority.getAuthority())) {
						
						isHaveFunc = true;
						
						break;
					}
				}
			}
			
			if(isHaveFunc) break;
				
		
		}
		if(isHaveFunc) return ACCESS_GRANTED;
		
		return ACCESS_DENIED;
	}

}
