<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> 호출주소 : http://localhost:8088/member/login</h1>
	<h1> 뷰페이지 :/member/login.jsp</h1>
	
	<h2> 로그인 </h2>
	<fieldset>
		<!-- form 태그에 action속성값이 없을때
			지금 실행중인 주소를 사용해서 이동(나자신의 주소 호출)
		 -->
		<form method="post">
			아이디 : <input type="text" name="userid"> <br>
			비밀번호 : <input type="password" name="userpw"> <hr>
			
			<input type="submit" value="로그인">
			<input type="button" value="회원가입"
					onclick="location.href='/member/join';">
		
		</form>
	
	</fieldset>
	

</body>
</html>