<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	String msg = (String) request.getAttribute("msg");
	String loc = (String) request.getAttribute("loc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<script>
		alert('<%=msg%>');
		location.href = "<%=request.getContextPath() +loc%>";
	</script>

</body>
</html>