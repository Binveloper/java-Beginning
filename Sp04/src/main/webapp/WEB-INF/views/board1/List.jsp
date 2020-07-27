<%@page contentType="text/html;charset=utf-8"
import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<title> Board ( MVC Paging JSTL + EL ) </title>
	<meta charset="utf-8">
	<style>
		a{text-decoration:none}
	</style>
</head>
<body>
<center>
<font color='gray' size='4' face='나눔고딕'>
<hr width='600' size='2' color='gray' noshade>
<h3> Spring Board</h3>
<font color='gray' size='4' face='나눔고딕'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.do'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>

<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>writer</th>
	<th color='gray'>e-mail</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
</TR>

	<c:if test = "${empty listResult.list}">
		<tr>
			<td align='center' colspan="5" >데이터가 없음</td>
		</tr>
	</c:if>
	<c:forEach items="${listResult.list}" var ="board">
            <tr>
            <td align='center'>
            ${board.seq}</td>
            <td>${board.writer}</td>
            <td>${board.email}</td>
            <td align='center'><a href='content.do?seq=${board.seq}'> ${board.subject}</a></td>
            <td>${board.rdate}</td>
            </tr>
</c:forEach>
       
      
</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='나눔고딕'>
    (총페이지수 : ${listResult.totalPageCount})
    &nbsp;&nbsp;&nbsp;
<c:forEach begin="1" end="${listResult.totalPageCount}" var="i">
        <a href='list.do?cp=${i}'>
        <c:choose>
        	<c:when test="${i==listResult.currentPage}">
            <strong>${i}</strong>
			</c:when>
			<c:otherwise>
            ${i}
            </c:otherwise>
        </c:choose>
    	</a>&nbsp;

  </c:forEach>
    ( TOTAL : ${listResult.totalPageCount})
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
	<c:choose>
		<c:when test="${listResult.pageSize == 3}">
			    <option value="3" selected>3</option>
		       <option value="5">5</option>
		       <option value="10">10</option>
		</c:when>
		<c:when test="${listResult.pageSize == 5}">
			   <option value="3">3</option>
		       <option value="5" selected>5</option>
		       <option value="10">10</option>
		</c:when>	
		<c:otherwise>
				<option value="3">3</option>
		       <option value="5">5</option>
		       <option value="10" selected>10</option>
		</c:otherwise>
	</c:choose>
    </select>
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href='list.do?&ps='+ps;
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>
    
</center>
</body>
</html>