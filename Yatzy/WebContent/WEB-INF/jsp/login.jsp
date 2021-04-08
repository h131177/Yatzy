<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h1>Login:</h1>
<p>
	<font color="red">${error}</font>
</p>
<form method="post" action="login" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="username">Username:</label> <input type="text" name="username" />
			</div>
			<div class="pure-control-group">
				<label for="password">Password:</label> <input type="password"
					name="password" />
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Login</button>
			</div>
		</fieldset>
	</form>
</body>
</html>