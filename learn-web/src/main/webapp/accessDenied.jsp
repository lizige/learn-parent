<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    
    <font color="red">${SPRING_SECURITY_403_EXCEPTION.message}</font>

    <br>
     <input type="button" onclick="history.go(-1);"  value="返回" />
</body>
</html>