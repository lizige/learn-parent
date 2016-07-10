<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
<link rel="stylesheet" href="<c:url value='/resource/js/bootstrap/css/bootstrap.min.css'/>">
     
<link rel="stylesheet" href="<c:url value='/resource/extjs4.2/resources/css/ext-all.css'/>">

</head>

<script type="text/javascript" src="<c:url value='/resource/extjs4.2/ext-all.js' />"></script>
<script type="text/javascript">

   function query() {
	   
	   form.submit();
   }
   
   function pageQuery(pageIndex) {
	   
	   $("#page.pageIndex").val(pageIndex);
   }
   
   function deleteInstance(id)
   {
	   Ext.Msg.confirm("任务处理","您确认删除此流程实例么?",function(opt) {
   		if(opt=='yes')
   		{
	   		 var url = "<c:url value='/workflow/deleteProcessInstance?processInstanceID=' />"+id;
	  	     form.action = url;
	  	     form.submit();
   		}
   		
      	});
	   
	  
	   
	   
   }
</script>
</head>
<body>
  <s:form  method="post" modelAttribute="form" cssClass="form-horizontal">
    <s:hidden path="page.pageIndex" id="page.pageIndex"/>
    <s:hidden path="page.pageSize"/>
     <div class="panel panel-default">
         <div class="panel-heading">已部署流程</div>
         <div class="panel-body">
		     <table width="98%" class="table table-bordered">
		           <thead> 
		              <tr> 
		                <th>流程实例ID</th>
		                <th>流程定义名称</th>
		                <th>流程定义ID</th>
		                <th>流程实例状态</th>
		                <th>操作</th>
		              </tr>
		           </thead>
		           <c:forEach var="po" items="${form.page.results}">
		              <tr>
		                 <td><c:out value="${po.id}"></c:out> </td>
		                 <td><c:out value="${po.processDefinitionName}" /></td>
		                 <td><c:out value="${po.processDefinitionId}"></c:out></td>
		                 <td><c:if test="${po.active}">激活</c:if> 
		                     <c:if test="${!po.active}">挂起</c:if>
		                 </td>
		                 <td>
		                    <a href="#"  onclick="deleteInstance(${po.id})">删除流程</a>
		                 </td>
		              </tr>
		           </c:forEach>
		     </table>
	     </div>
	    
     </div>
    </s:form>
</body>
</html>