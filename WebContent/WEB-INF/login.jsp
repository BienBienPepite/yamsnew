<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>

<body>

	<form method="post" action="">
	
		<p>
			<label for="username">Username : 
				<input type="text" name="username" value="<c:out value='${ user.username }'/>">
			</label>
		</p>
		<c:if test="${ form.errors['username'] != null }">
			<p class="error">${ form.errors['username'] }<p>
		</c:if>
		
		<p>
			<label for="password">Password : </label>
			<input type="password" name="password">
		</p>
		<c:if test="${ form.errors['password'] != null }">
			<p class="error">${ form.errors['password'] }<p>
		</c:if>
		
		<p>
			<input type="submit" class="button" name="confirm" value="Confirm">
		</p>
	
	</form>
	
	
	<form action="menu">
		<input type="submit" class="button" name="back" value="Back">
	</form>
	
</body>
</html>