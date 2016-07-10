package com.wht.system.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="t_system_permission")
@DiscriminatorValue("9")
@DiscriminatorColumn(name="owner_type")
public class Permission extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Function function;


	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=Function.class
			  ,cascade={CascadeType.MERGE})
	@JoinColumn(name="function_ID")
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	

}
