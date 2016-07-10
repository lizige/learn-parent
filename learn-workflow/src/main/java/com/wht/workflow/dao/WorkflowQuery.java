package com.wht.workflow.dao;



import java.util.List;
import java.util.Map;


import com.wht.workflow.model.TaskVO;

public interface WorkflowQuery {

	
	public List<Map<String, Object>>  getMyTaskList(TaskVO condition,int size);
}
