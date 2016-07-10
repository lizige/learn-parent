package com.wht.system.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_system_permission")
@DiscriminatorValue("2")
public class RoleFunction extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Role role ;

	
	@ManyToOne(targetEntity=Role.class
			  ,cascade={CascadeType.MERGE})
	@JoinColumn(name="role_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
