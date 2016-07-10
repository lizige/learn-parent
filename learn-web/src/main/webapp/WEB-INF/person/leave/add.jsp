<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html>
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

   function save()
   {
       leaveForm.submit();
   }
   
   function sub()
   {
       $("#isSubmit").val("1");
       leaveForm.submit();
   }
   </script>
</head>
<body>
    
    <div class="panel panel-default" style="top:50px;left:50px;position: absolute;width:80%;">
      <div class="panel-heading">请假申请单</div>
      <div class="panel-body">
      <s:form id="leaveForm" method="POST" modelAttribute="leave" cssClass="form-horizontal">
          <s:hidden path="id" />
          <input  id="isSubmit" type="hidden" name="isSubmit" value="0" />
		  <div class="form-group">
		    <s:label path="beginDate" cssClass="col-md-2 control-label" >请假开始日期</s:label>
		    <div class="col-md-10"><s:input path="beginDate"  width="380px" /></div> 
		  </div>
		  <div class="form-group">
		    <s:label path="endDate" cssClass="col-md-2 control-label">请假结束日期</s:label>
		    <div class="col-md-10"><s:input path="endDate" width="380px" /></div>
		  </div>
		  <div class="form-group">
		    <s:label path="remark" cssClass="col-md-2 control-label">请假理由</s:label>
		    <div class="col-md-10"><s:textarea path="remark" rows="3" cssStyle="width:380px"/></div>
		  </div>
		  <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
               <a href="<c:url value='/person/leave/list' />" class="btn btn-default">取 消</a>
               <button type="button" class="btn btn-default" onclick="save();">保 存</button>
		       <button type="button" class="btn btn-default" onclick="sub();">提  交</button>
		     </div>
		  </div>
      </s:form>
     </div> 
    </div>
</body>
</html>