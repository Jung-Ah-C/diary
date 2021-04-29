<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember</title>
</head>
<body>
	<h1>modifyMember</h1>
	<form method="post" action="${pageContext.request.contextPath}/auth/modifyMember">
		<div>
			<div>ID: </div>
			<div><input type="text" name="memberId" value="${sessionMember.memberId}" readonly="readonly"></div>
			<div>PW: </div>
			<div><input type="password" name="newMemberPw" required="required"></div>
			<div><button type="submit">비밀번호 수정</button></div>
		</div>
	</form>
</body>
</html>