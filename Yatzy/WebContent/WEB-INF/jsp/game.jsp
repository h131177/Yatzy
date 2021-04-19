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
<style>
input[type="checkbox"] {
	margin: 20px;
}
</style>
</head>
<body>
	<h1>Yatzy</h1>

	<h3>Player logged in: ${loggedIn.username}</h3>
	<form method="get" action="game">
		<button type="submit" class="pure-button pure-button-primary">Update</button>
	</form>

	<table class="pure-table">
		<tr>
			<th bgcolor="#cccccc">Dice</th>
			<c:forEach items="${numbers}" var="n">
				<td>${n.value}<img alt="${n.value}" src="dice${n.value}.PNG"
					width="50px" height="50px"></td>
			</c:forEach>
			<td>
				<form method="post" action="game">
					<button type="submit" class="pure-button pure-button-primary">Done</button>
				</form>
			</td>
		</tr>
		<tr>
			<th bgcolor="#cccccc">Hold</th>
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
	<p>
		You have ${3-counter} <img alt="${3-counter}"
			src="dice${3-counter}.PNG" width="25px" height="25px"> rolls
		left!
	</p>
	<br>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Player</th>
			<th>${loggedIn.username}</th>
		</tr>
		<c:forEach items="${info}" var="i" varStatus="k">
			<!-- Legge inn if og else for å sjekke om det er spesial rader, som sum, bonus osv -->
			<c:choose>
				<c:when test="${k.index == row}">
					<tr bgcolor="#aaffaa">
				</c:when>
				<c:otherwise>
					<tr bgcolor="#ffffff">
				</c:otherwise>
			</c:choose>

			<td align="center">${i}</td>
			<c:forEach items="${points[k.index]}" var="out" varStatus="loop">
				<c:choose>
					<c:when test="${k.index == 6}">
						<td>${sum[0]}</td>
					</c:when>
					<c:when test="${k.index == 17}">
						<td>${total[0]}</td>
					</c:when>
					<c:otherwise>
						<td>${points[k.index][loop.index]}</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>