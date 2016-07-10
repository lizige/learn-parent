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
</script>
</head>
<body>
  <s:form method="post" cssClass="form-horizontal">
     <div class="panel panel-default">
         <div class="pannel-heading">
             <label>请假管理</label>
         </div>
         <div class="pannel-body">
               <p class="text-center">
			        <input type="submit" class="btn btn-default" value="查  询" />
			        <a  href="add"  class="btn btn-default" >新  增</a>
			    </p>
         </div>
     </div>
     <div class="panel panel-info">
         <h4>请假申请</h4>
         <div class="panel-body">
	     <table width="98%" class="table table-hover">
	           <thead> 
	              <tr> 
	                <th>申请单ID</th>
	                <th>申请人</th>
	                <th>请假开始时间</th>
	                <th>请假结束时间</th>
	                <th>操作</th>
	              </tr>
	           </thead>
	           <c:forEach var="po" items="${form.page.results}">
	              <tr>
	                 <td>${po.id}</td>
	                 <td><c:out value="${po.applyUser.name}"></c:out> </td>
	                 <td><fmt:formatDate value="${po.beginDate}" pattern="yyyy-MM-dd"/> </td>
	                 <td><fmt:formatDate value="${po.endDate}" pattern="yyyy-MM-dd"/>  </td>
	                 <td>
	                      <a href="${po.id}/update" class="btn btn-default">修  改</a>
	                      <a href="${po.id}/delete" class="btn btn-default">删  除</a>
	                 </td>
	              </tr>
	           </c:forEach>
	     </table>
	     </div>
     </div>
    </s:form>
</body>
</html>