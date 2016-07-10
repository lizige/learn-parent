package com.wht.system.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_system_permission")
@DiscriminatorValue("1")
public class UserFunction extends Permission {


	private static final long serialVersionUID = -7308879940010782537L;
	
	
	private User user ;

	@ManyToOne(targetEntity=User.class
			  ,cascade={CascadeType.MERGE})
	@JoinColumn(name="user_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
}
