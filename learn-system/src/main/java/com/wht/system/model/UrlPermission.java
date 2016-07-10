package com.wht.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.access.ConfigAttribute;


@Entity
@Table(name="t_system_url_permission")
public class UrlPermission implements ConfigAttribute{

	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String url;
	
	private String roleCode;

	@Transient
	public String getAttribute() {
		
		return this.roleCode;
	}

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
	
}
