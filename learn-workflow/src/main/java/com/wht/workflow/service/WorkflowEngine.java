package com.wht.workflow.service;

import java.util.Map;

public interface WorkflowEngine {


	public String startProcessInstance(String key,String businessKey,String userID,Map<String, Object> varis);
	
	public String startProcessInstance(String key,String businessKey,String userID);

}
