<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
		<h1>ITWILL.jsp</h1>
		
		JSP : <%=request.getParameter("msg") %> <hr>
		EL  : ${param.msg } <hr>
		
		EL(Spring) : ${msg }<hr>
		EL(Spring) - page : ${pageScope.msg }<hr>
		EL(Spring) - request : ${requestScope.msg }<hr>
		EL(Spring) - session : ${sessionScope.msg }<hr>
		EL(Spring) - application : ${applicationScope.msg }<hr>
		
		<hr>
		<h2> 객체 전달하기 (SampleController3) </h2>
		http://localhost:8088/web/doC 실행<hr>
		vo : ${vo } <hr>
		vo : ${requestScope.vo } <hr>
		vo(익명) : ${memberVO } <hr>
		vo(익명) : ${requestScope.memberVO } <hr>
		
		
		
		
		
		
		
</body>
</html>