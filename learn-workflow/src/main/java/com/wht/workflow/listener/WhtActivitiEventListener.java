package com.wht.workflow.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class WhtActivitiEventListener implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		
		System.out.println(event.getType().name());
	}

	@Override
	public boolean isFailOnException() {
		
		return false;
	}

	
}
