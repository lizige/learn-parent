package com.wht.system.spring;

import javax.sql.DataSource;

public class Hibernates {

	private DataSource dataSource;
	
	public Hibernates(DataSource dataSource) {
	
		this.dataSource = dataSource;
	}
	
	public String getDialect() {
		
		return null;
	}

}
