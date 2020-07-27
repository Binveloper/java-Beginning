<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">

<!-- 4. Javascript -->
<script type="text/javascript" src="../js/paging.js"></script>

</head>
<body>
    <p align="center">
    <img src="../images/daum.png" width="200" height="100"/>
    <a href="../">인덱스</a>
    </p>
    
	<div class="container">
	
		<select class='btn btn-primary' id='listCount' name='listCount' onchange='listCnt();'>
			<option value='5' >5</option>
			<option value='10'>10</option>
			<option value='15'>15</option>
			<option value='20'>20</option>
		</select>
	
		<table class="table table-bordered">
			<tr>
			    <th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
				    <td>${dto.seq}</td>
					<td>${dto.subject}</td>
					<td>${dto.rdate}</td>
					<td><a href="del.do?seq=${dto.seq}">삭제</a></td>
				</tr>						
			</c:forEach>
		</table>
	
		<!-- 5. paging view -->	
		<ul class="pagination">
			<c:if test="${p.pageStartNum ne 1}">
				<!--맨 첫페이지 이동 -->
				<li><a onclick='pagePre(${p.pageCnt+1},${p.pageCnt});'>&laquo;</a></li>
				<!--이전 페이지 이동 -->
				<li><a onclick='pagePre(${p.pageStartNum},${p.pageCnt});'>&lsaquo;</a></li>
			</c:if>
			
			<!--페이지번호 -->
			<c:forEach var='i' begin="${p.pageStartNum}" end="${p.pageLastNum}" step="1">
				<li class='pageIndex${i}'><a onclick='pageIndex(${i});'>${i}</a></li>
			</c:forEach>
			
			<c:if test="${p.lastChk}">
				<!--다음 페이지 이동 -->
				<li><a onclick='pageNext(${p.pageStartNum},${p.total},${p.listCnt},${p.pageCnt});'>&rsaquo;</a></li>
				<!--마지막 페이지 이동 -->
				<li><a onclick='pageLast(${p.pageStartNum},${p.total},${p.listCnt},${p.pageCnt});'>&raquo;</a></li>
			</c:if>
		</ul>
		<form action="list.do" method="post" id='frmPaging'>
			<!--출력할 페이지번호, 출력할 페이지 시작 번호, 출력할 리스트 갯수 -->
			<input type='hidden' name='index' id='index' value='${p.index}'>
			<input type='hidden' name='pageStartNum' id='pageStartNum' value='${p.pageStartNum}'>
			<input type='hidden' name='listCnt' id='listCnt' value='${p.listCnt}'>	
		</form>
	
		<a href="write.do?num=1">글1개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=3">글3개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=5">글5개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=10">글10개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=20">글20개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=50">글50개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=100">글100개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=1000">글1000개추가</a>&nbsp;&nbsp;
		<a href="write.do?num=10000">글10000개추가</a>
		<br/><br/>
		<div><a href="delAll.do">모두삭제</a></div>
	</div>
</body>
</html>