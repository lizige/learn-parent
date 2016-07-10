<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resource/extjs4.2/resources/css/ext-all.css'/>">

</head>

<script type="text/javascript" src="<c:url value='/resource/extjs4.2/ext-all.js' />"></script>
<script type="text/javascript">
  
   Ext.require('Ext.tab.*');

  
  Ext.onReady(function() {
	  
	  Ext.widget('tabpanel', {
	        renderTo: 'tab1',
	        width: '100%',
	        activeTab: 0,
	        defaults :{
	            bodyPadding: 10
	        },
	        items: [{
	            contentEl:'markup', 
	            title: '任务列表'
	        },{
	            contentEl:'script', 
	            title: '代办列表'
	        }]
	    });
  });
</script>
<body>
  <div class="container-fluid">
       <div class="row" >
		   <div id="tab1" class="col-md-9">
	            <div id="markup" class="x-hide-display" >
		            <div  class="panel panel-default" >
		              <div class="panel-body" style="width:100%;height:100%;">
		                  		<table width="98%" class="table table-hover">
						           <thead> 
						              <tr> 
						                <th>任务ID</th>
						                <th>任务名称</th>
						                <th>办理人</th>
						                <th>到单时间</th>
						                <th>操作</th>
						              </tr>
						           </thead>
						           <c:forEach var="po" items="${myTasks}">
						              <tr>
						                 <td><c:out value="${po.id}"></c:out> </td>
						                 <td><c:out value="${po.name}"></c:out> </td>
						                 <td><c:out value="${po.assignee}"></c:out> </td>
						                 <td><fmt:formatDate value="${po.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
						                 <td>
						                   <c:if test="${po.assignee==null }">
						                     <a href="<c:url value='/task/viewTask?taskID=${po.id}' />" >查看签收任务</a>
						                     |&nbsp;&nbsp;<a href="<c:url value='/task/takeTask?taskID=${po.id}' />" >签收</a>
						                   </c:if>
						                   <c:if test="${po.assignee!=null }">
						                     <a href="<c:url value='/task/viewTask?taskID=${po.id}&isTake=0' />" >处理</a>
						                   </c:if>
						                  </td>
						              </tr>
						           </c:forEach>
						     </table>
		              </div>
		            </div>
		        </div>
		        <div id="script" class="x-hide-display">
	               <p>暂无代办信息</p>
	            </div>
     </div>
	  <div class="col-md-3 panel panel-info">
	    <div class="panel-heading">通知</div>
		  <div class="panel-body">
		      今天放假
		  </div>
	  </div>
  </div>
  </div>
  
     
</body>
</html>