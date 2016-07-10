package com.wht.system.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="t_system_user")
public class User {

	private Long id;
	
	private String name;
	
	private String loginName;
	
	private String loginPassword;
	
	private Date createTime;
	
	
	private Set<UserFunction> userFunctions;
	
	private Set<UserRole> userRoles;
	
	
	private List<String> roleCodeList = null;
	

	@OneToMany(mappedBy="user")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Column(name="login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name="login_password")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	@OneToMany(mappedBy="user")
	public Set<UserFunction> getUserFunctions() {
		return userFunctions;
	}

	public void setUserFunctions(Set<UserFunction> userFunctions) {
		this.userFunctions = userFunctions;
	}

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
	    
		return super.equals(obj);
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public synchronized List<String> getRoleCodeList() {
		
		if(this.roleCodeList == null && this.userRoles!=null) {
			
			this.roleCodeList = new ArrayList<String>();
			
			for(UserRole userRole:this.userRoles) {
				
				roleCodeList.add(userRole.getRole().getCode());
			}
		}
		
		return roleCodeList;
	}

	public void setRoleCodeList(List<String> roleCodeList) {
		this.roleCodeList = roleCodeList;
	}

    
	
	
}
