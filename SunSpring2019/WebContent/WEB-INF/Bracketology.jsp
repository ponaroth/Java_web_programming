<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>Bracketology</title>
</head>
<body>
	<form action='Team' method='post'>

		<c:forEach items="${Teams}" var = "Teams" varStatus = "i">
		<p>
		
		<span>Match ${i.index+1}</span>
		
		<label>${Teams.team1}</label><input type= "radio"name="Match${Teams.id}" value="${Teams.team1}"/>
		<label>${Teams.team2}</label><input type= "radio"name="Match${Teams.id}" value="${Teams.team2}"/>
		
		</p>
		</c:forEach>
		
		<button>Next</button>
	</form>
</body>
</html>