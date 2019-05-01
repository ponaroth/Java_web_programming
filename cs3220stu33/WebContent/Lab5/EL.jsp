<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>


<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #eeeeee;
}

code {
	white-space: nowrap;
	padding: 2px 5px;
	color: red;
	background-color: #e7e7e7;
	font-size: 100%;
}

</style>

<meta charset="UTF-8">
<title>Lab 5: EL</title>
</head>
<body>
	<table>
		<tr>

			<th colspan="2">Arithmetic Operations</th>
			<th colspan="2">Relational Operation</th>
		</tr>
		<tr>
			<th>Expression</th>
			<th>Result</th>
			<th>Expression</th>
			<th>Result</th>
		</tr>
		<tr>
			<td><a>$</a>{3+2-1}</td>
			<td>${3+2-1}</td>
			<td><a>$</a>{1<2}</td>
			<td>${1<2}</td>
		</tr>
		<tr>
			<td><a>$</a>{"1"+2}</td>
			<td>${"1"+2}</td>
			<td><a>$</a>{"a"<"b"}</td>
			<td>${"a"<"b"}</td>
		</tr>

		<tr>
			<td><a>$</a>{1+2*3+3/4}</td>
			<td>${1+2*3+3/4}</td>
			<td><a>$</a>{2/3>=3/2}</td>
			<td>${2/3>=3/2}</td>


		</tr>
		<td><a>$</a>{3%2}</td>
		<td>${3 % 2}</td>
		<td><a>$</a>{3/4 == 0.75}</td>
		<td>${3/4 == 0.75}</td>


		<tr>
			<td><a>$</a>{(8 div 2) mod 3}</td>
			<td>${(8 div 2) mod 3}</td>
			<td><a>$</a>{null == "test"}</td>
			<td>${null == "test"}</td>

		</tr>

		<tr>

			<th colspan="2">Local Operators</th>
			<th colspan="2"><code>Empty </code> Operator</th>
		</tr>

		<tr>
			<th>Expression</th>
			<th>Result</th>
			<th>Expression</th>
			<th>Result</th>
		</tr>

		<tr>
			<td><a>$</a>{(1<2) && (4<3)}</td>
			<td>${(1<2) && (4<3)}</td>
			<td><a>$</a>{empty ""}</td>
			<td>${empty ""}</td>
		</tr>



		<tr>

			<td><a>$</a>{(1<2) || (4<3)}</td>
			<td>${(1<2) || (4<3)}</td>
			<td><a>$</a>{empty null}</td>
			<td>${empty null}</td>

		</tr>

		<tr>

			<td><a>$</a>{!(1<2)}</td>
			<td>${!(1<2)}</td>
			<td><a>$</a>{empty param.blah}</td>
			<td>${empty param.blah}</td>

		</tr>

	</table>

</body>
</html>