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
				<td><input type="checkbox" id="check0" name="check0" value="0"></td>
				<td><input type="checkbox" id="check1" name="check1" value="1"></td>
				<td><input type="checkbox" id="check2" name="check2" value="2"></td>
				<td><input type="checkbox" id="check3" name="check3" value="3"></td>
				<td><input type="checkbox" id="check4" name="check4" value="4"></td>
				<td><button type="submit"
						class="pure-button pure-button-primary">Roll</button> <input
					type="hidden" value="roll" + name="roll"></td>
			</form>
		</tr>

	</table>
	<p>You have ${3-counter} rolls left!</p>
	<br>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Player</th>
			<th>Kjetil</th>
		</tr>
		<c:forEach items="${info}" var="i" varStatus="k">
			<tr bgcolor="#ffffff">
				<td align="center">${i}</td>
				<c:forEach items="${points[k.index]}" var="out" varStatus="loop">
				<!--<c:forEach items="${points[loop.index]}" var="inn" varStatus="innerloop">-->
				<td>${points[k.index][loop.index]}</td>
				<!--</c:forEach>-->
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>