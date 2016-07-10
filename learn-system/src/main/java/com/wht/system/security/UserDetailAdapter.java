package com.wht.system.security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wht.system.model.User;
 
public class UserDetailAdapter implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailAdapter() {
		
	}
	
	public UserDetailAdapter(User user) {
		
		this.user = user;
	}
	
	public User getRealUser() {
		
		return this.user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.user.getUserRoles();
	}
	
	

	@Override
	public String getPassword() {
		return this.user.getLoginPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailAdapter other = (UserDetailAdapter) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.getLoginName().equals(other.user.getLoginName()))
			return false;
		return true;
	}
	
	
}
