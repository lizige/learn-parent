<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="common/common.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
    
    <%
        String isFailed = request.getParameter("failed");
        if(isFailed!=null&&isFailed.equals("true")) {
	        Exception exception =  (Exception)session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	        pageContext.setAttribute("exception", exception);
        }
   
    %>

    <div class="panel panel-default" style="top:200px;left:300px;position: absolute;width:50%;">
       <form action="login" method="POST" class="form-horizontal">
       <c:if test="${exception!=null}">
	       <div class="form-group" id="error">
			  <label for="inputEmail3" class="col-md-2 control-label">用户名</label>
			  <div class="col-md-10">
			    <font color="red" class="form-control">${exception.message}</font>
			  </div>
		   </div>
	   </c:if>
       <div class="form-group">
		  <label for="inputEmail3" class="col-md-2 control-label">用户名</label>
		  <div class="col-md-10">
		    <input type="text" class="form-control" name="loginName" style="width:200px;"/>
		  </div>
	   </div>
       <div class="form-group">
		   <label for="inputEmail3" class="col-md-2 control-label">密码:</label>
		   <div class="col-md-10">
		      <input type="text" class="form-control" name="loginPassword" style="width:200px;"/>
		   </div>
	   </div>
	   <div class="form-group">
		    
		    <div class="col-md-offset-2 col-md-10">
		      <label> 
		          <input type="checkbox"  name="_remember_wo" value="1">记住密码
		      </label>
		   </div>
		   
		  
	   </div>
	   <div class="form-group">
           <div class="col-md-offset-2 col-sm-10">  
             <input type="submit" value="提  交" />   
           </div>
       </div>
    </form> 
    </div>
    
</body>
</html>