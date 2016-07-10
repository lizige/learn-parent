package com.wht.system.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Table(name="T_SYSTEM_USER_ROLE")
@Entity
public class UserRole extends BasePO implements GrantedAuthority {

	private Long id;
	

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	

	@JoinColumn(name="user_id")
	@ManyToOne(cascade={CascadeType.ALL})
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@JoinColumn(name="role_id")
	@ManyToOne(cascade={CascadeType.ALL})
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	private Role role;


	@Transient
	public String getAuthority() {
		return this.role.getCode();
	}


	
	

}
