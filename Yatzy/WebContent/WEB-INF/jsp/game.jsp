<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Yatzy Game</title>
</head>
<body>
	<h1>Yatzy</h1>

	<h3>Player logged in: ${loggedIn.username}</h3>

	<table class="pure-table">
		<tr>
			<th bgcolor="#cccccc">Dice</th>
			<c:forEach items="${numbers}" var="n">
				<td>${n.value}</td>
			</c:forEach>
			<td>
				<form method="post" action="game">
					<button type="submit" class="pure-button pure-button-primary">Done</button>
				</form>
			</td>
		</tr>
		<tr>
			<th bgcolor="#cccccc">Check</th>
			<form method="post" action="game">
			<td><input type="checkbox" id="check1" name="check1" value="1"></td>
			<td><input type="checkbox" id="check2" name="check2" value="2"></td>
			<td><input type="checkbox" id="check3" name="check3" value="3"></td>
			<td><input type="checkbox" id="check4" name="check4" value="4"></td>
			<td><input type="checkbox" id="check5" name="check5" value="5"></td>
			<td><button type="submit"
					class="pure-button pure-button-primary">Roll</button> <input
				type="hidden" value="roll" + name="roll"></td>
			</form>
		</tr>

	</table>
	<br>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Player</th>
			<th>Kjetil</th>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Enere</td>
			<td>5</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Enere</td>
			<td>5</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Enere</td>
			<td>5</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Enere</td>
			<td>5</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Enere</td>
			<td>5</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Enere</td>
			<td>5</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td align="center">Sum</td>
			<td>5</td>
		</tr>
	</table>
</body>
</html>