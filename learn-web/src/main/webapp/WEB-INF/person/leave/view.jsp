<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
     
<link rel="stylesheet" href="<c:url value='/resource/js/bootstrap/css/bootstrap.min.css'/>">
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
    <div class="panel panel-default" style="top:50px;left:50px;position: absolute;width:80%;">
      <div class="panel-heading">请假申请单</div>
      <div class="panel-body">
      
      <s:form  method="POST"  cssClass="form-horizontal" cssStyle="form-horizontal">
          <input type="hidden" name="workflowID" value="${workflowID}" />
          <input type="hidden" name="taskID" value="${taskID}" />
		  <div class="form-group">
		    <div class="col-sm-2">请假开始日期</div>
		    <div class="col-sm-10"><fmt:formatDate value="${leave.beginDate }"/></div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-2">请假结束日期</div>
		    <div class="col-sm-10"><fmt:formatDate value="${leave.endDate }"/> </div>
		  </div>
		  <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
               <a href="<c:url value='/person/leave/list' />" class="btn btn-default">取 消</a>
		       <button type="submit" class="btn btn-default">提  交</button>
		     </div>
		  </div>
      </s:form>
     </div> 
    </div>
</body>
</html>