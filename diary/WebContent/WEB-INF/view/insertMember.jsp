<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertMember</title>
</head>
<body>
	<h1>insertMember</h1>
	<!-- form이 InsertMemberController로 넘어감 -->
	<form method="post" action="${pageContext.request.contextPath}/insertMember">
		<div>
			<div>ID: </div>
			<div><input type="text" name="memberId" required="required"></div>
			<div>PW: </div>
			<div><input type="password" name="memberPw" required="required"></div>
			<div><button type="submit">가입</button></div>
		</div>
	</form>
</body>
</html>