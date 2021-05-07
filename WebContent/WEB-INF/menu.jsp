<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yamsnew</title>
</head>
<body>

	<h1>YAMSNEW</h1>
	
	<ul class="buttons_list">
		
		<c:choose>
		
			<c:when test="${ empty sessionScope.user }">
				<li>
					<form action = "register">
						<input type="submit" name="register" class="button" value="Register">
					</form>	
				</li>
				<li>
					<form action = "login">
						<input type="submit" name="login" class="button" value="Log in">
					</form>	
				</li>
			</c:when>
			
			<c:otherwise>
			
				<p>${ sessionScope.user.username }</p>
				
				<li>
					<form method="post" action="game"> 
						<input type="submit" name="game" class="button" value="Play">
					</form>
				</li>
				
				<li>
					<form action = "settings">
						<input type="submit" name="settings" class="button" value="Settings">
					</form>	
				</li>
				
				<li>
					<form action ="${ sessionScope.user = null }">
						<input type="submit" name="logout" class="button" value="Log out">
					</form>	
				</li>
			</c:otherwise>
			
			
		</c:choose>
		
	</ul>
</body>
</html>