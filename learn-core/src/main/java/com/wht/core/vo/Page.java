package com.wht.core.vo;

import java.util.List;

public class Page {

	private Integer pageIndex;
	
	private Integer pageSize;
	
	private List results;
	
	private Integer firstResult;
	
	

	public Page() {
		
	}
	
	
	
    public Page(Integer pageIndex,Integer pageSize) {
		
    	this.pageIndex = pageIndex;
    	
    	this.pageSize = pageSize;
	} 

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}
	
	public Integer getFirstResult() {
		
		return (this.pageIndex-1)*this.getPageSize()+1;
	}

	

}
