<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL : 공유 내장객체 page request session application에 있는 값 가져올 때</title>
</head>
<body>
	<table>
		<tr>
			<th>이름</th>
			<th>레벨</th>
			<th>캐릭터</th>
			<th>경험치</th>
		</tr>
		<tr>
			<td>${requestScope.name }</td>
			<td>${level }</td>
			<td>${character }</td>
			<td>${exp }</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>주소</th>
		</tr>
		<%-- <c:forEach var="list" item="${list }"> --%>
		<tr>
			<td>${list.get(0)["name"] }</td>
			<td>${list.get(0)["age"] }</td>
			<td>${list.get(0)["address"] }</td>
		</tr>
		<tr>
			<td>${list.get(1).name}</td>
			<td>${list.get(1).age}</td>
			<td>${list.get(1).address}</td>
			
		</tr>
		<!-- </c:forEach> -->
	</table>
	<h1>${list }</h1>
</body>
</html>