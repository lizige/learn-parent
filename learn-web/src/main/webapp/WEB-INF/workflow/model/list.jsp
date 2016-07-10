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
   <div class="panel panel-defaul">
     <div class="panel-head">
        <h3>工作流定义管理</h3>
     </div>
     <div class="panel-body">
	     <table width="98%" class="table">
	          <tr> 
	             <td>
	                <div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">定义名称</span>
					  <s:input path="model.name" cssStyle="width:280px;" />
					</div>
	             </td>
	              <td>
	                <div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">定义KEY</span>
					 <s:input path="model.key" cssStyle="width:280px;" />
					</div>
	             </td>
	          </tr>                 
	     </table>
	     <p class="text-center">
	        <input type="button" class="btn btn-default" onclick="query();" value="查  询" />
	        <a  href="add"  class="btn btn-default" >新  增</a>
	        
	        <a  href="/test/workflow/deployBPMN"  class="btn btn-default" >部署流程定义</a>
	     </p>
     </div>
    </div>
     <div class="panel panel-info">
         <h4>工作流定义列表</h4>
         <div class="panel-body">
		     <table width="98%" class="table table-hover">
		           <thead> 
		              <tr> 
		                <th>模型ID</th>
		                <th>模型KEY</th>
		                <th>模型名称</th>
		                <th>模型类别</th>
		                <th>创建时间</th>
		                <th>操作</th>
		              </tr>
		           </thead>
		           <c:forEach var="po" items="${form.page.results}">
		              <tr>
		                 <td><c:out value="${po.id}"></c:out> </td>
		                 <td><c:out value="${po.key}"></c:out> </td>
		                 <td><c:out value="${po.name}"></c:out> </td>
		                 <td><c:out value="${po.category}"></c:out> </td>
		                 <td><fmt:formatDate value="${po.createTime}" pattern="yyyy-MM-dd"/> </td>
		                 <td>
		                      <a href="<c:url value='/workflow/model/${po.id}/update'/>" class="btn btn-default">修  改</a>
		                      <a href="<c:url value='/workflow/model/${po.id}/delete' />" class="btn btn-default">删  除</a>
		                      <a href="<c:url value='/modeler?modelId=${po.id}' />" class="btn btn-default">更改流程</a>
		                 </td>
		              </tr>
		           </c:forEach>
		     </table>
	     </div>
	    
     </div>
    </s:form>
</body>
</html>