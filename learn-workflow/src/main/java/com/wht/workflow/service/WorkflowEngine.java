package com.wht.workflow.service;

import java.util.Map;

public interface WorkflowEngine {

	/**
	 * 
	 * @param key   流程定义标识
	 * @param businessKey  用户业务标识
	 * @param userID  启动用户ID
	 * @param varis  本地变量
	 * @return  流程实例ID
	 */
	public String startProcessInstance(String key,String businessKey,String userID,Map<String, Object> varis);
	
	public String startProcessInstance(String key,String businessKey,String userID);

}
