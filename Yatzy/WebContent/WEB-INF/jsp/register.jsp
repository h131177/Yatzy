<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Register</title>
</head>
<body>
<h1>Register User</h1>
<form method="post" action="register">
        <fieldset>
          <label for="name">Name:</label> <input type="text" name="name" id="name" />
          <br>
          <label for="username">Username:</label> <input type="text" name="username" id="username" />
          <br>
          <label for="password">Password:</label> <input type="password" name="password" id="password" />
          <br>
          <label for="email">Email:</label> <input type="email" name="email" id="email" />
          <br>
          <button type="submit" class="pure-button pure-button-primary">Create user</button>
        </fieldset>
</form>
<p>Already have a user?</p>
<form method="login" action="login">
		<button type="submit" class="pure-button pure-button-primary">Login</button>
</form>
</body>
</html>