<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Address List with Spring5 + Mybatis</title>
		<style>
			table, th, td {
			   border: 1px solid black;
			   border-collapse: collapse;
			}
			th, td {
			   padding: 5px;
			}
			a { text-decoration:none }
		</style>
	</head>

	<body style="text-align:center">
		<center>
			<h1>
				Address List with Spring5 + Mybatis
			</h1>
			<a href='write.do'>입력</a><br/>
			<table border='1' cellpadding='7' cellspacing='2' width='50%'>
			<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>날짜</th>
			<th>삭제</th>
			</tr>
			
			<c:if test="${empty list}">
			<tr>
				<td colspan="5" align="center">데이터가 하나도 없음</td>
			</tr>
			</c:if>
			
			<c:forEach items="${list}" var="address">
			<tr>
				<td align='center'>${address.seq}</td>
				<td>${address.name}</td>
				<td>${address.addr}</td>
				<td>${address.rdate}</td>
				<td align='center'><a href='del.do?seq=${address.seq}'>삭제</a></td>
			</tr>
			</c:forEach>
			
			</table>
		</center>
	</body>
</html>