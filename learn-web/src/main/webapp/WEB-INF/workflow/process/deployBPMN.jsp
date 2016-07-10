<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<link rel="stylesheet" href="<c:url value='/resource/js/bootstrap/css/bootstrap.min.css'/>">
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
   
   </script>
</head>
<body>
    
    <div class="panel panel-default" style="top:50px;left:50px;position: absolute;width:80%;">
      <div class="panel-heading">部署BPMN文件</div>
      <div class="panel-body">
	      <s:form action="" method="POST" cssClass="form-horizontal" enctype="multipart/form-data">
	
			  <div class="form-group">
			    <label>BPMN文件</label>
			    <input type="file" name="upFile" width="180px;" >
			  </div>
			  <div class="form-group">
	             <div class="col-sm-offset-2 col-sm-10">
	               <a href="<c:url value='/workflow/model/list' />" class="btn btn-default">取 消</a>
			       <button type="submit" class="btn btn-default">提  交</button>
			     </div>
			  </div>
	      </s:form>
     </div> 
    </div>
</body>
</html>