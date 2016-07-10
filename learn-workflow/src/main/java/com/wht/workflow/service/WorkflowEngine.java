package com.wht.workflow.service;

import java.util.Map;

public interface WorkflowEngine {

	/**
	 * 
	 * @param key   ���̶����ʶ
	 * @param businessKey  �û�ҵ���ʶ
	 * @param userID  �����û�ID
	 * @param varis  ���ر���
	 * @return  ����ʵ��ID
	 */
	public String startProcessInstance(String key,String businessKey,String userID,Map<String, Object> varis);
	
	public String startProcessInstance(String key,String businessKey,String userID);

}
