<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!-- sticky -->
<c:set var="temp" value="" />
<c:set var="temp2" value="" />

<c:if test="${not empty param.submitQuery}">
	<c:if test="${not empty param.query}">
		<c:set var="temp" value="${param.query}" />

	</c:if>
</c:if>


<c:if test="${not empty param.submitUpdate}">
	<c:if test="${not empty param.query}">
		<c:set var="temp2" value="${param.query}" />

	</c:if>
</c:if>


<%-- set data source --%>
<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://cs3.calstatela.edu/cs3220stu107" user="cs3220stu107"
	password="1us7Dj0q" />


<%-- submit query function --%>
<c:if test="${not empty temp}">
<sql:query var="theTable">

			<c:out value="${temp}" />
		
</sql:query>
</c:if>

<c:if test="${not empty temp2}">


<c:choose>
<c:when test="${not empty temp2}">
<sql:update var="tableUpdate">

			<c:out value="${temp2}" />
</sql:update>

<sql:query var="theTable">
       select * from items
 </sql:query>

</c:when>
		
	<c:otherwise>
      
    </c:otherwise>
    
</c:choose>
</c:if>






<c:if test="${empty param.query}">
	<p style="color: red;">No Query submitted. For example, try "select
		* from items"</p>
</c:if>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Lab7 JSTL SQL</title>


<style>

.grid {
width: 500px;
height: 100px;
}
</style>

</head>

<body>


	<c:if test="${theTable.rowCount == 0}">
		<p>There is no item.</p>
	</c:if>


	<!-- Submit Query and update buttons -->
	<form action="Lab7.jsp" method="post">
		Enter your query: <br>
		<textarea name="query" class="grid">${param.query}</textarea>
		<br> <input type="submit" name="submitQuery" value="Submit Query" />
		<input type="submit" name="submitUpdate" value="Submit Update" />


		<!-- update query waring -->
		<c:choose>
			<c:when test="${updateError != null || queryError != null}">
				<i style="color: red"> Enter valid values <br> <c:out
						value="${updateError.message}" /> <c:out
						value="${queryError.message}" />
				</i>
			</c:when>

			<c:when test="${not empty param.query}">
				<p>
					Displaying <strong><c:out value="${theTable.rowCount}" /></strong>
					results
					
				</p>
				

				<p>
					Submitted query:
					<code style="color: red">
						<c:out value="${param.query}" />
					</code>
				</p>
			</c:when>

			<c:when test="${not empty param.update}">
				<p>
					Successfully updated table with <strong>
					<c:out value="${results.rowCount}" /></strong>
					<code style="color: red">
						<c:out value="${param.queryBox}" />
					</code>
				</p>
			</c:when>
		</c:choose>

	</form>

	<!-- try catch -->
	<c:catch var="queryError">
		<c:if test="${not empty param.query}">
			<sql:query var="results" dataSource="${source}"
				sql="${param.queryBox}" />
		</c:if>
	</c:catch>

	<c:catch var="updateError">
		<c:if test="${not empty param.update}">
			<sql:update var="updates" dataSource="${source}">
        ${param.queryBox}
      </sql:update>
		</c:if>
	</c:catch>

	<c:if test="${theTable.rowCount > 0}">
		<table class="table table-bordered table-striped table-hover"
			border="1">
			
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<!-- <th>Action</th> -->
			</tr>

			<c:forEach items="${theTable.rowsByIndex}" var="row">


				<tr>
					<c:forEach items="${row}" var="col">
						<td>${col}</td>
					</c:forEach>
					<%-- <td><a href="Delete?id=${entry.id}">Delete</a></td> --%>
				</tr>
			</c:forEach>
		</table>
	</c:if>





</body>
</html>