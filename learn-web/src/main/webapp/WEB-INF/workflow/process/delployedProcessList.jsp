<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
<link rel="stylesheet" href="<c:url value='/resource/js/bootstrap/css/bootstrap.min.css'/>">
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

   function query() {
	   
	   form.submit();
   }
   
   function pageQuery(pageIndex) {
	   
	   $("#page.pageIndex").val(pageIndex);
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
		     <table width="98%" class="table table-bordered table-hover ">
		           <thead> 
		              <tr> 
		                <th>流程ID</th>
		                <th>流程KEY</th>
		                <th>流程名称</th>
		                <th>流程版本</th>
		                <th>流程状态</th>
		                <th>操作</th>
		              </tr>
		           </thead>
		           <c:forEach var="po" items="${form.page.results}">
		              <tr>
		                 <td><c:out value="${po.id}"></c:out> </td>
		                 <td><c:out value="${po.key}" /></td>
		                 <td><c:out value="${po.name}"></c:out></td>
		                 <td><c:out value="${po.version}"></c:out></td>
		                 <td><c:if test="${po.suspensionState==1}">激活</c:if> 
		                     <c:if test="${po.suspensionState==2}">挂起</c:if>
		                 </td>
		                 <td>
		                      <c:if test="${po.suspensionState==1}">
		                          <a href="<c:url value='/workflow/${po.id}/viewProcessDiagram' />" >查看流程图</a>
		                          <a href="<c:url value='/workflow/${po.id}/viewProcessXML' />" >查看流程XML</a>
		                          <a href="<c:url value='/task/preStartProcess?processDefinitionId=${po.id}' />" >启动流程</a>
		                      </c:if>
		                 </td>
		              </tr>
		           </c:forEach>
		     </table>
	     </div>
	    
     </div>
    </s:form>
</body>
</html>