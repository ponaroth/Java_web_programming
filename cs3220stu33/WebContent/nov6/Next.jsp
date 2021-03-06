 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="gb" class="models.GuestBookBean" scope="application" />

<c:if test="${not empty param.add}">
  <%-- invoke setAddEntry() to create an empty entry --%>
  <jsp:setProperty name="gb" property="addEntry" value="dummy" />
  <%-- set name & message into the new entry --%>
  <c:set target="${gb.lastEntry}" property="name" value="${param.name}" />
  
  <c:redirect url="Bracket.jsp" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

</head>
<body>
<form action="Next.jsp" method="post">
Name: <input type="text" name="name" /> <br />
<!-- Message: <input type="text" name="message" /> <br /> -->
<input type="submit" name="add" value="Add" />
</form>
</body>
</html>
