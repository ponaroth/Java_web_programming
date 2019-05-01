<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<style>

.grid {
text-align: center;
width: 70px;
height: 70px;
}
</style>


<meta charset="ISO-8859-1">
<title>Lab 6 MVC Tic tac toe </title>
</head>
<body>

<h1>Tic Tac Toe</h1>
<h2><font color="grey">${sessionScope.theGame.getGameMessage()}</font></h2>

<h5>turn count: ${sessionScope.theGame.count}</h5>

<div>
<a href='NewGameController'><button>New Game</button></a>

</div>

<div>
<a style="text-decoration: none;" href='PlayController?id=0' ><button class="grid"><h1>${sessionScope.Board[0]}<h1></button></a>
<a style="text-decoration: none;" href='PlayController?id=1'><button class="grid"><h1>${sessionScope.Board[1]}<h1></button></a>
<a style="text-decoration: none;" href='PlayController?id=2'><button class="grid"><h1>${sessionScope.Board[2]}<h1></button></a>
</div>

<div>
<a style="text-decoration: none;" href='PlayController?id=3'><button class="grid"><h1>${sessionScope.Board[3]}<h1></button></a>
<a style="text-decoration: none;" href='PlayController?id=4'><button class="grid"><h1>${sessionScope.Board[4]}<h1></button></a>
<a style="text-decoration: none;" href='PlayController?id=5'><button class="grid"><h1>${sessionScope.Board[5]}<h1></button></a>
</div>

<div>
<a style="text-decoration: none;" href='PlayController?id=6'><button class="grid"><h1>${sessionScope.Board[6]}<h1></button></a>
<a style="text-decoration: none;" href='PlayController?id=7'><button class="grid"><h1>${sessionScope.Board[7]}<h1></button></a>
<a style="text-decoration: none;" href='PlayController?id=8'><button class="grid"><h1>${sessionScope.Board[8]}<h1></button></a>
</div>

<br>


</body>
</html>