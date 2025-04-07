<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 호출주소 : http://localhost:8088/member/login</h1>
	<h1> 뷰 페이지 : /member/main.jsp</h1>
	
	<h2> 로그인한 사용자 아이디 : ${id }</h2>
	<h2> 로그인한 사용자 아이디 : ${sessionScope.id }</h2>
	
	<input type="button" value="로그아웃"
			onclick="location.href='/member/logout';">
	
	<hr>
	
	<h3><a href="/member/info">회원정보 조회</a></h3>
	
	<h3><a href="/member/update">회원정보 수정</a></h3>

	<h3><a href="/member/delete">회원정보 삭제</a></h3>
	
	<!-- 아이디가 userid일때, 회원 목록정보 조회 -->
	<c:if test="${!empty id && id.equals('admin') }">
		<h3><a href="/member/list">회원정보 목록 조회</a></h3>
	</c:if>
	
	
	
</body>
</html>