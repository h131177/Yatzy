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
<title>Yatzy Lobby</title>
</head>
<body>
	<h1>Lobby</h1>
	<!-- Create game button
- Start game button -->
	<form method="post" action="lobby">
		<button type="submit" class="pure-button pure-button-primary">Create
			game</button>
	</form>
	<br>
	<form method="post" action="lobby">
		<button type="submit" class="pure-button pure-button-primary">Start
			game</button>
	</form>
	<br>
	<!-- Join game dropdown list and button
- View your games dropdown list and button
- Log out button/link -->
	<form method="post" action="lobby">
		<label for="games">Choose a game:</label> <select name="games" id="games">
			<option value="game1">Game 1</option>
			<option value="game2">Game 2</option>
		</select>
		<br>
		<button type="submit" class="pure-button pure-button-primary">Join
			game</button>
	</form>
	<br>
	<form method="post" action="lobby">
		<label for="oldgames">Choose a game:</label> <select name="oldgames" id="oldgames">
			<option value="oldgame1">Game 1</option>
			<option value="oldgame2">Game 2</option>
		</select>
		<br>
		<button type="submit" class="pure-button pure-button-primary">View
			game</button>
	</form>
	<br>
	<a href="lobby">Log out</a>
</body>
</html>