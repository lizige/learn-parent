package com.wht.core.web;

import com.wht.core.vo.Page;

public class BaseForm {

	private Page page = new Page(1,10);

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
