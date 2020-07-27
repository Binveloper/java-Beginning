<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
<%
	boolean flag = (Boolean)request.getAttribute("flag");
%>
--%>

	<script language='javascript'>
		if(${flag}){ //자바스크립트의 if
			alert("수정 성공 by MVC~");
		}else{
			alert("수정 실패 by MVC~");
		}
		location.href="list.do";
	</script>