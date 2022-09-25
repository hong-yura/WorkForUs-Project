<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>근태표</title>
</head>
<body>
	<c:forEach items="${listData}" var="commuteData" varStatus="status">
		<tr class="table-light">
			<td>
				${commuteData.commuteDt.substring(4,6)}월 ${commuteData.commuteDt.substring(6)}일
		</td>
		<td>
			${commuteData.commuteTime.substring(11)}
		</td>
		<td>
			${commuteData.getoffTime.substring(11)}
		</td>
		<td>
			<c:choose>
				<c:when test="${empty commuteData.getoffTime}">
					-
				</c:when>
				<c:otherwise>
					${commuteData.addedTime.substring(11, 13)}h ${commuteData.addedTime.substring(14, 16)}m ${commuteData.addedTime.substring(17)}s 													
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${empty commuteData.getoffTime}">
					-
				</c:when>
				<c:otherwise>
					${commuteData.workTime.substring(11, 13)}h ${commuteData.workTime.substring(14, 16)}m ${commuteData.workTime.substring(17)}s
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
		</body>
</html>