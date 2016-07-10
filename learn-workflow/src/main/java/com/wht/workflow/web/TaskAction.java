package com.wht.workflow.web;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.IdentityLinkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wht.system.security.SecurityContextUtils;
import com.wht.system.service.UserService;


@Controller
@RequestMapping("/task")
public class TaskAction {
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private UserService userService;
	@Autowired
	private HistoryService historyService;
	

	@RequestMapping(value="/preStartProcess")
	public String preStartProcess(@RequestParam String processDefinitionId,ModelMap m,HttpServletRequest request){
		
		 StartFormData formData =  this.formService.getStartFormData(processDefinitionId);
		 
		 m.put("formData", formData);
		 
		 m.put("submitUrl",request.getContextPath()+"/task/startProcessByForm");

		 return "workflow/task/startTask";
	}
	
	@RequestMapping(value="/startProcessByForm")
	public String startProcessByForm(@RequestParam String processDefinitionId,HttpServletRequest request){
		
		 StartFormData formData =  this.formService.getStartFormData(processDefinitionId);
		 
		
		 Map<String, String> properties = new HashMap<String, String>();
		 
		 for(FormProperty formProperty : formData.getFormProperties()) {
			 
			String value =  request.getParameter(formProperty.getId());
			
			properties.put(formProperty.getId(), value);
		 }
		 
		 identityService.setAuthenticatedUserId(SecurityContextUtils.getCurUser().getLoginName());

		 formService.submitStartFormData(processDefinitionId, properties);
		 
		 
		 return "redirect:/welcome";
	}
	
	/**
	 * �鿴����
	 * @param taskID
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/viewTask")
	public String viewTask(@RequestParam String taskID,ModelMap model){
		
		TaskEntity task = (TaskEntity)this.taskService.createTaskQuery().taskId(taskID).active().singleResult();
			
        String formKey =  task.getFormKey();
        
        if(formKey!=null&&!"".equals(formKey)) { //ת���Զ���?
        	
        	String url = formKey+"?workflowID="+task.getProcessInstanceId()+"&taskID="+task.getId();
        	
        	return "forward:"+url;
        	
        }else {
        	
           ProcessDefinition processDefinition = this.repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        	
           TaskFormData taskFormData = this.formService.getTaskFormData(taskID);
           
           ExecutionEntity processInstance = (ExecutionEntity)this.runtimeService.createProcessInstanceQuery()
        		                                                .processInstanceId(task.getProcessInstanceId())
        		                                                .singleResult();
           
           
           
           model.put("formData", taskFormData);
           
           model.put("processName", processDefinition.getName());
           
           String applyUser= (String)this.runtimeService.getVariable(task.getExecutionId(), "applyUser");
           
           if(applyUser!=null) {
        	   String applyUserName = this.userService.getUserByLoginName(applyUser).getName();
               
               model.put("applyUser",applyUserName);
        	   
           }
          

           List<Comment> comments= this.taskService.getProcessInstanceComments(task.getProcessInstanceId());
           
           model.put("comments", comments);
           
           return "workflow/task/processTask";

        }

		//return "forward:/welcome";
	}
	
	@RequestMapping(value="/takeTask")
	public String takeTask(@RequestParam String taskID,HttpServletRequest request){
		
		String takeAndResolve = request.getParameter("takeAndResolve");
		
		this.taskService.claim(taskID, SecurityContextUtils.getCurUser().getLoginName());
		
		String url = "redirect:/welcome";
		
		if("1".equals(takeAndResolve)) {
			
			url = "forward:resolveTask";
		}

		return url;
	}
	
	@RequestMapping(value="/resolveTask")
	public String resolveTask(@RequestParam String taskID,HttpServletRequest request){
		

        Map<String, String> properties = new HashMap<String, String>();
        
        TaskFormData taskFormData = this.formService.getTaskFormData(taskID);
        
		for(FormProperty formProperty : taskFormData.getFormProperties()) {
			 
			String value =  request.getParameter(formProperty.getId());
			if(formProperty.isWritable())
			   properties.put(formProperty.getId(), value);
		}
		
		String comment = request.getParameter("comment");
		
		identityService.setAuthenticatedUserId(SecurityContextUtils.getCurUser().getLoginName());
		
		CommentEntity commentEntity = (CommentEntity)this.taskService.addComment(taskFormData.getTask().getId(), taskFormData.getTask().getProcessInstanceId(), comment);

        this.formService.submitTaskFormData(taskID, properties); 
		
		return "forward:/welcome";
	}
	
	
	
}
