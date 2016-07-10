<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>

<%

   String menuStr = (String)request.getAttribute("menuStr");

   String basepath = request.getContextPath();
%>
<script type="text/javascript">

function showMenu(url)
{

	  if(url=="")
	  {
		  alert("未设置URL");
		  
		  return;
	  }
	  $("#content").attr("src","<%=basepath%>"+url);
  
}


$(function() {
	

	var menuStr = '<%=menuStr%>';
	
	var menuList  = $.parseJSON(menuStr);

	var menus = "";
	for(var i=0;i<menuList.length;i++)
	{
		var menu = menuList[i];
		
		var m =getMenuStr(menu);
		
		menus+=m;
	
	}
	
	$("#ul_menu").append(menus);
	
});

function getMenuStr(menu)
{
	var menuUrl = "";

	if(menu.urlOrMethod &&menu.urlOrMethod!=null)
		menuUrl = menu.urlOrMethod;

	if(menu.children.length==0)
	{
		return "<li><a href='#' onclick=\"showMenu('"+menuUrl+"')\">"+menu.name+"</a></li>";
	}
	
	var  m = "<li class='dropdown'>";
	m+= "<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"+menu.name+"<b class='caret'></b></a>";
	m+= "<ul class='dropdown-menu'>";
	for(var i=0;i<menu.children.length;i++)
	{
		var child = menu.children[i];
		
		var childStr = getMenuStr(child);
		
		m+=childStr;
	}
	m+= "</ul>";
	m+= "</li>";
	
	return m;
	
}

</script>


</head>
 <body style="margin-top:70px;">
 <div class="navbar navbar-default navbar-fixed-top" role="navigation">
   <div class="container-fluid">
       <div class="navbar-header">
           <a class="navbar-brand" href="#" onclick="showMenu('/welcome')">个人首页</a>
       </div>
	   <div class="collapse navbar-collapse">
	      <ul  id="ul_menu" class="nav navbar-nav">
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	         <li role="separator" class="nav-divider"></li>
	         <li><a class="navbar-brand" href="#">欢迎【${userName}】登陆</a></li>
	         <li><a href="logout" class="btn btn-default">系统注销</a></li>
	      </ul>
	   </div>
   </div>
 </div>
</body>
 <div class="embed-responsive embed-responsive-4by3" >
   <iframe id="content" src="welcome" >
   </iframe>
</div>


    

</html>