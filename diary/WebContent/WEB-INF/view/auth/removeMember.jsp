<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/auth/removeMember">
		<div>
			memberPw :
		</div>
		<div>
			<input type="password" name="memberPw">
		</div>
		<div>
			<button type="submit">회원탈퇴</button>
		</div>
	</form>
</body>
</html>