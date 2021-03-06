<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
</head>
<body>
	<c:set var="totalCell" value="${diaryMap.startBlank + diaryMap.endDay + diaryMap.endBlank}"></c:set>
	<div>totalCell : ${totalCell}</div>
	
	<h1><a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth-1}">이전달</a>
		${diaryMap.targetYear}년 ${diaryMap.targetMonth + 1}월
		<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}">다음달</a>
	</h1>
	
	<table border="1" width="90%">
		<tr>
			<c:forEach var="i" begin="1" end="${totalCell}" step="1">
				<c:set var="num" value="${i-diaryMap.startBlank}"></c:set>
				<td>
					<c:if test="${num > 0 && num <= diaryMap.endDay}">
						<a href="${pageContext.request.contextPath}/auth/addTodo?year=${diaryMap.targetYear}&month=${diaryMap.targetMonth + 1}&day=${num}">
							<div>${num}</div>
							<div>
								<c:forEach var="todo" items="${diaryMap.todoList}">
									<c:if test="${todo.todoDate == num}">
										<!-- todoOne 상세보기, 수정, 삭제e -->
										<div style="background-color:${todo.todoFontColor}">
											<a href="${pageContext.request.contextPath}/auth/todoOne">${todo.todoTitle}...</a>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</a>
					</c:if>
					<c:if test="${num <= 0 || num > diaryMap.endDay}">
						&nbsp;
					</c:if>
				</td>
				<c:if test="${i%7==0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>