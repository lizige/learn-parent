package com.wht.system.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_system_function")
public class Function  {

	private Long id;
	
	private String name;
	
	private String code;
	
	private String urlOrMethod;
	
	private Integer type;
	
	private String orderNo;
	
	private List<Function> children;
	
	private Long parentID;
	


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public String getUrlOrMethod() {
		return urlOrMethod;
	}



	public void setUrlOrMethod(String urlOrMethod) {
		this.urlOrMethod = urlOrMethod;
	}

    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	
	@OneToMany(mappedBy="parentID")
	public List<Function> getChildren() {
		return children;
	}

	public void setChildren(List<Function> children) {
		this.children = children;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

    
	
}
