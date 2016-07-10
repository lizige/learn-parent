package com.wht.workflow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.activiti.engine.task.IdentityLinkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.wht.core.utils.StringUtil;
import com.wht.workflow.model.TaskVO;

@Repository
public class WorkflowQueryImpl extends JdbcDaoSupport implements WorkflowQuery {

	@Autowired
	private void  setForJdbcTemplate(JdbcTemplate jdbcTemplate) {
		
		this.setJdbcTemplate(jdbcTemplate);
	}
	
	public List<Map<String, Object>>  getMyTaskList(TaskVO condition,int size){
		
		
		String select = "select a.ID_ taskID,a.PROC_INST_ID_ workflowID,b.NAME_ workflowName ,a.NAME_ taskName"
				      + " ,a.CREATE_TIME_ createTime ";
		
		String from = " from act_ru_task a , act_re_procdef  b ,act_ru_execution c ,act_ru_identitylink d ";
		
		
		StringBuffer where = new StringBuffer(" where a.PROC_DEF_ID_= b.id_"); 
        where.append(" and a.PROC_INST_ID_=a.PROC_INST_ID_ and c.IS_ACTIVE_='1' and c.ACT_ID_=a.TASK_DEF_KEY_ ");
        where.append(" and a.PROC_INST_ID_ = d.PROC_INST_ID_   and d.task_id_=a.ID_");
		
		List<Object> paramList = new ArrayList<Object>();
		
		if(!StringUtil.isNullOrEmpty(condition.getCandidateGroup())) {
			
			where.append(" and  d._type=? and a.GROUP_ID_=?");
			
			paramList.add(IdentityLinkType.CANDIDATE);
			
			paramList.add(condition.getCandidateGroup());
		}
		
       if(!StringUtil.isNullOrEmpty(condition.getCandidateUser())) {
			
			where.append(" and  d.type_=? and d.USER_ID_=?");
			
			paramList.add(IdentityLinkType.PARTICIPANT);
			
			paramList.add(condition.getCandidateUser());
		}
       

       
       if(!StringUtil.isNullOrEmpty(condition.getTaskAssignee())) {
			
			where.append(" and  d.type_=? and d.USER_ID_=?");
			
			paramList.add(IdentityLinkType.ASSIGNEE);
			
			paramList.add(condition.getTaskAssignee());
		}
       
       String sql = select +from + where.toString();
		
	   return this.getJdbcTemplate().queryForList(sql, paramList.toArray());
		
	}

	
}
