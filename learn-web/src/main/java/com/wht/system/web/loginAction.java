package com.wht.system.web;


import java.util.List;

import org.activiti.engine.TaskService;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wht.system.model.Function;

import com.wht.system.security.CurrentUser;
import com.wht.system.security.UserDetailAdapter;
import com.wht.system.service.SystemService;

import com.wht.workflow.dao.WorkflowQuery;



@Controller
@RequestMapping()
public class loginAction {

	@Autowired
	private SystemService systemService;
	@Autowired
    private WorkflowQuery workflowQuery;
	
	@Autowired
	private  TaskService taskService;
	
	@RequestMapping(value="/index")
	public ModelAndView index(@CurrentUser UserDetailAdapter user) throws JsonProcessingException {
		
		ModelAndView mv = new ModelAndView();
		
		List<Function> menuList = this.systemService.getFunctionByType(5L, 2);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String menuStr = objectMapper.writeValueAsString(menuList);
		
		mv.setViewName("index");
		
		mv.addObject("userName",user.getRealUser().getName());
		
		mv.addObject("menuStr",menuStr);
		
		System.out.println(menuStr);
		
		return mv;
	}
	
	@RequestMapping(value="/welcome")
	public ModelAndView welcome(@CurrentUser UserDetailAdapter user) throws JsonProcessingException {
		
		ModelAndView mv = new ModelAndView();

		mv.setViewName("welcome");
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(user.getUsername()).active().orderByTaskCreateTime().desc().list();
		
		tasks.addAll(this.taskService.createTaskQuery().taskCandidateGroupIn(user.getRealUser().getRoleCodeList()).active().list()) ;
		
		mv.addObject("myTasks", tasks);
		
		return mv;
	}
	
}
