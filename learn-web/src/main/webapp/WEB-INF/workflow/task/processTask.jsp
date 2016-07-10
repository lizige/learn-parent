<%@page import="java.util.Map"%>
<%@page import="org.activiti.engine.form.FormProperty"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
     
<link rel="stylesheet" href="<c:url value='/resource/js/bootstrap/css/bootstrap.min.css'/>">
     
<link rel="stylesheet" href="<c:url value='/resource/extjs4.2/resources/css/ext-all.css'/>">
       
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<c:url value='/resource/extjs4.2/ext-all-debug.js' /> "></script>
<title>Insert title here</title>
 
   <style type="text/css">
    input.error { border: 1px solid red; }
	label.error {
		background:url(“./demo/images/unchecked.gif”) no-repeat 0px 0px;
		padding-left: 16px;
		padding-bottom: 2px;
		font-weight: bold;
		color: #EA5200;
    }
   
   </style>
    <script type="text/javascript" src="<c:url value='/resource/js/validate/jquery.validate.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/resource/js/validate/messages_zh.min.js'/>"></script>
   
   <script type="text/javascript">
   
    $.validator.setDefaults({
	    submitHandler: function() {
	    	
	    	 <c:if test="${oper==2}">
	    	    user.action="../update";
	    	 </c:if>
	    	
	         user.submit();
	    }
	});
	$(function() {
	    $("#user").validate({
	    	rules:{
	    		name:"required"
		       ,loginName:{
		    			 required:true
		    			 ,minlength:4
		    			 ,maxlength:10
		    	}
	           ,loginPassword:"required"
	    	}
	      ,errorPlacement: function(error, element) {
	    	   //alert(element.attr("id"));
	    	   error.insertAfter(element);
	      }
	    });
	});
   </script>
</head>
<body>
    
  <div class="panel panel-default" style="top:50px;left:50px;position: absolute;width:80%;">
      <div class="panel-heading">【${processName}】-【${formData.task.name}】</div>
      <div class="panel-body">
	      <form id="form" action="" method="POST" class="form-horizontal" >
	          
	          <input type="hidden" name="taskID" value="${formData.task.id}" />
	          <input id="takeAndResolve" type="hidden" name="takeAndResolve" value="0" />
	          <div class="form-group">
					<label class="col-sm-2 control-label">申请人</label>
					<div class="col-sm-10"><div class="form-control">${applyUser}</div></div>
	          </div>
	          <c:forEach var="pro" items="${formData.formProperties}">
	                <c:set var="formPro" value="${pro}"></c:set>
	                <%
	                     FormProperty formProperty = (FormProperty)pageContext.getAttribute("formPro");
	                %>
	                <% if(formProperty.isReadable()|| formProperty.isWritable()) { %>
			            <div class="form-group">
						    <label class="col-sm-2 control-label">${pro.name}:</label>
						    <div class="col-sm-10">
						    <% if(!formProperty.isWritable()) { %>
						         <div class="form-control">${pro.value}</div>
						     <%}else {
						    	 if(formProperty.getType().getName().equals("enum")) {
						    	
						    		Map<String,String> enumValues = (Map<String,String>)formProperty.getType().getInformation("values");
						          %>
						         <select name="${pro.id}" value="${pro.value}" class="form-control">
						           <%
						              for(String key :enumValues.keySet()) {
						           %>
						              <option value="<%=key%>"><%=enumValues.get(key)%></option>
						           <%} %>
						         </select>
						        
						       <%}else { %> 
						    	 <input type="text" name="${pro.id}" value="${pro.value}" class="form-control"  />
						       <%} %>
						     <%} %>
						     </div>
					    </div>
		           <%} %>
	          </c:forEach>
	          <div class="form-group">
					<label class="col-sm-2 control-label">意见列表</label>
					<div class="col-sm-10">
					     <table>
					         <c:forEach var="comment" items="${comments}">
					             <tr>
					                 <td>
					                     ${comment.message} &nbsp; ${comment.taskId} &nbsp; ${comment.userId}
					                      &nbsp;<fmt:formatDate value="${comment.time}" pattern="yyyy-MM-dd HH:mm"/>  
					                 </td>
					             </tr>
					         </c:forEach>
					     </table>
					</div>
	          </div>
	          <div class="form-group">
					<label class="col-sm-2 control-label">审批意见</label>
					<div class="col-sm-10"><textarea rows="2" name="comment" class="form-control"></textarea></div>
	          </div>
	          <div class="form-group">
	              <c:if test="${formData.task.assignee==null}">
				     <div class="col-sm-offset-2"><input type="button" class="btn btn-default" onclick="takeTask()" value="签  收"/>
				     &nbsp;
				     <input type="button" class="btn btn-default" onclick="takeAndResolveTask()" value="签收处理"/></div>
				  </c:if> 
				  <c:if test="${formData.task.assignee!=null}">
				     <div class="col-sm-offset-2"><input type="button" class="btn btn-default" onclick="resolveTask()" value="处  理"/></div>
				  </c:if>
			  </div>
	      </form>
     </div> 
   </div>
</body>
 <script type="text/javascript">
    function takeTask() {
    	Ext.Msg.confirm("任务签收","您确认签收此任务吗?",function(opt) {
    		if(opt=='yes')
    		{
    			form.action="<c:url value='/task/takeTask' />";
    	    	form.submit();
    		}
    		
    	});
    	
    	
    }
    
    function takeAndResolveTask() {
    	$("#takeAndResolve").val("1");
    	Ext.Msg.confirm("任务处理","您确认处理此任务吗?",function(opt) {
    		if(opt=='yes')
    		{
    			form.action="<c:url value='/task/takeTask' />";
    	    	form.submit();
    		}
    		
    	});
	
    }
    
    function resolveTask() {
    	Ext.Msg.confirm("任务处理","您确认签收此任务吗?",function(opt) {
    		if(opt=='yes')
    		{
    			form.action="<c:url value='/task/resolveTask' />";
    	    	form.submit();
    		}
    		
    	});
    	
    }
 
 </script>
</html>