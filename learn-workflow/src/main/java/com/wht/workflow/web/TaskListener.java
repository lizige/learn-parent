package com.wht.workflow.web;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;

import com.wht.core.utils.StringUtil;

public class TaskListener implements org.activiti.engine.delegate.TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {

//		String approve = (String) delegateTask.getVariable("approve");
//		
//		Long approveCount = (Long) delegateTask.getVariable("approveCount");
//		
//		if(!StringUtil.isNullOrEmpty(approve)) {
//			if(approve.equals("true")){
//				approveCount+=1;
//			}
//		}
//		delegateTask.setVariable("approveCount", approveCount);
		
		String type = (String)delegateTask.getVariable("startType");
		
		RuntimeService runtimeService = delegateTask.getExecution().getEngineServices().getRuntimeService();
		
		if(type.equals("1")) {
			
			runtimeService.startProcessInstanceByMessage("֪ͨ");
		}else if(type.equals("2")) {
			runtimeService.signalEventReceived("֪ͨ");
		}else {
			
		}
		
	}

	
}
