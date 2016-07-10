<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../common/common.jsp" %>
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
   <div class="panel panel-defaul">
     <div class="panel-heading">
        <h3>用户管理</h3>
     </div>
     <div class="panel-body">
	     <table width="98%" class="table">
	          <tr> 
	             <td>
	                <div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">用户名字</span>
					  <input type="text" class="form-control" name="name" style="width:280px;">
					</div>
	             </td>
	              <td>
	                <div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">登录名</span>
					  <input type="text" class="form-control" name="loginName" style="width:280px;">
					</div>
	             </td>
	          </tr>                 
	     </table>
	     <p class="text-center">
	        <input type="submit" class="btn btn-default" value="查  询" />
	        <a  href="add"  class="btn btn-default" >新  增</a>
	     </p>
     </div>
    </div>
     <div class="panel panel-info">
         <h4>用户列表</h4>
         <div class="panel-body">
	     <table width="98%" class="table table-hover">
	           <thead> 
	              <tr> 
	                <th>用户ID</th>
	                <th>用户名字</th>
	                <th>登录名</th>
	                <th>用户密码</th>
	                <th>操作</th>
	              </tr>
	           </thead>
	           <c:forEach var="po" items="${userList}">
	              <tr>
	                 <td>${po.id}</td>
	                 <td><c:out value="${po.name}"></c:out> </td>
	                 <td><c:out value="${po.loginName}"></c:out> </td>
	                 <td><c:out value="${po.loginPassword}"></c:out> </td>
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