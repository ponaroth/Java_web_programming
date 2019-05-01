<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestBook</title>
</head>
<body>
	<h2>GuestBook</h2>
	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Specialties</th>
			<th>Presentation</th>
			<th>Rating</th>
			<th>Operations</th>
		</tr>

		<c:forEach items="${entries}" var="entry">
			<tr>
				<td style="text-align: center;">${entry.id}</td>
				<td><a style="text-decoration: none; "href='Rating?id=${entry.id}'>${entry.name}</a></td>
				<td>${entry.specialties}</td>
				<td>${entry.presentation}</td>
				
				<c:choose>
				<c:when test="${entry.rating == 0}"> <td style="text-align: center;">N/A</td></c:when>
				<c:otherwise> <td style="text-align: center;">${entry.rating}</td></c:otherwise>
				</c:choose>
				
				<td>
					<center>
						<a style="text-decoration: none;" href='Edit?id=${entry.id}'>Edit</a>
					</center>
				</td>

			</tr>
		</c:forEach>
	</table>
	<p>
		<a style="text-decoration: none;" href="Add">Add Candidate</a>
	</p>

</body>
</html>