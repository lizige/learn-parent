package com.wht.workflow.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.ExecutionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowEngineImpl implements WorkflowEngine {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Override
	public String startProcessInstance(String key,String businessKey,String userID,Map<String, Object> varis) {
		
		ProcessInstance processInstance =   this.runtimeService.startProcessInstanceByKey(key);
		
		List<Task> tasks = this.taskService.createTaskQuery().processInstanceId(processInstance.getId())
		                                  .active().list();
		
		for(Task task : tasks) {
			
			this.taskService.complete(task.getId(),varis);
		}
		
		return processInstance.getId();
	}

	@Override
	public String startProcessInstance(String key, String businessKey, String userID) {
	
		return this.startProcessInstance(key, businessKey, userID, null);
	}
	
	public void getOu(String taskID,String processionInstanceID,String processDefinitionId) {
		
		Task task = this.taskService.createTaskQuery().taskId(taskID).singleResult();
		
	    ReadOnlyProcessDefinition definition =  ((RepositoryServiceImpl)this.repositoryService)
	    		                                  .getDeployedProcessDefinition(processDefinitionId);
	
	    
	}

	
}
