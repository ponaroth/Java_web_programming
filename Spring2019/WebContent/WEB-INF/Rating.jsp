<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta charset="UTF-8">

	<title>Rate Candidate</title>
</head>
<body>

	<p>
		<a href="Hw4">Back to Candidates</a>
	</p>

	<form action="Rating" method="post">
		<table cellspacing="2" cellpadding="2" border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Specialties</th>
					<th>Presentation</th>
					<th>Rating</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${entry.id}</td>
					<td>${entry.name}</td>
					<td>${entry.specialties}</td>
					<td>${entry.presentation}</td>
					<td style="text-align: center;">${entry.rating}</td>
					
				</tr>
			</tbody>
		</table>
		
		
	
		<p>Comments:</p>
		
		<table cellspacing="2" cellpadding="5" border="1">
			<tbody>
			
				<c:forEach items="${entry.raterList}" var="rev">
				<tr>
					<td>Rating: ${rev.rating}</td>
					<td style="text-align: right;">Posted by ${rev.rater} on <fmt:formatDate value="${rev.date}" type="date" />
				</tr>
				<tr>
					<td colspan="2">${rev.comment}&nbsp;</td>

				</tr>
				</c:forEach>
				
			</tbody>
		</table>


<!-- feed back -->
<!--  -->
		<p>Please give your feedback:</p>

		<table cellspacing="2" cellpadding="2" border="1">
			<tbody>

					<tr>
						<th>Rating</th>
						<td>1 <input name="rating" type="radio" value=1> 
							<input name="rating" type="radio" value=2> 
							<input name="rating" type="radio" value=3> 
							<input name="rating" type="radio" value=4> 
							<input name="rating" type="radio" value=5> 5</td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input name="rater" size="40"></td>
					</tr>
					<tr>
						<th>Comments</th>
						<td><textarea cols="60" name="comment" rows="5"
								style="background-color: rgb(240, 240, 240);"></textarea></td>
					</tr>
					<tr>

<!-- 				<td colspan="2">
 --><!-- 					 <input name="save" type="submit" value="Save" />
 -->				<!-- </td> -->

				<td colspan="2" rowspan="1"><input type="hidden" name="id"
					value="${entry.id}" /> <input name="save" type="submit" value="Save"/>
				</td>
				</tr>
				
			</tbody>
		</table>

	</form>

</body>
</html></html>