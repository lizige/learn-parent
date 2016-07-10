package com.wht.system.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wht.system.model.User;

public class SecurityContextUtils {

	public static User getCurUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        
        if(principal instanceof UserDetailAdapter)
            return ((UserDetailAdapter)principal).getRealUser();
        
        return null;
		
	}
}
