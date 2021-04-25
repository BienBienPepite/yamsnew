<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game</title>
</head>
<body>

	
	
	<form method="post">
		
		
		<c:forEach var="dice" items="${game.currentHand}" varStatus="status">
				<input type="checkbox" id="dices" name="dices" value="${status.count}">${ dice }
		</c:forEach>
		
		
		<button type="submit" id="roll" name="roll" value="roll">Roll</button>
		
	</form>
	
	<c:if test="${ form.errors['roll'] != null }">
		<p>${ form.errors['roll'] }</p>
	</c:if>
	
	<p>${ game.currentDiceRoll }</p>
	
</body>
</html>