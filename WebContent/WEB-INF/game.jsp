<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Game</title>
</head>
<body>
	
	<p>${ game.currentDiceRoll }</p>
	
	<form method="post">
		
		<c:forEach var="dice" items="${game.currentHand}" varStatus="status">
				<input type="checkbox" id="dices" name="dices" value="${status.count}">${ dice }
		</c:forEach>
		
		<button type="submit" id="roll" name="roll" value="roll">Roll</button>
		
	</form>
	
	<c:if test="${ form.errors['roll'] != null }">
		<p>${ form.errors['roll'] }</p>
	</c:if>
	
	<form method="post">
	
		<table>
		
			<tr>
				<td>ACE</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['ace']}">
							<input type="radio" id="box" name="box" value="ace">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['ace']}
						</c:otherwise>
					</c:choose>
				</td>
				<td rowspan="16">
					<button type="submit" id="fill" name="fill" value="fill">Fill</button>
				</td>
			</tr>
		
			<tr>
				<td>TWO</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['two']}">
							<input type="radio" id="box" name="box" value="two">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['two']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td>THREE</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['three']}">
							<input type="radio" id="box" name="box" value="three">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['three']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td>FOUR</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['four']}">
							<input type="radio" id="box" name="box" value="four">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['four']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td>FIVE</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['five']}">
							<input type="radio" id="box" name="box" value="five">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['five']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td>SIX</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['six']}">
							<input type="radio" id="box" name="box" value="six">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['six']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td>UPPER SECTION TOTAL</td>
				<td>
					${game.bonusAndTotalGrid['uppersectiontotal']}
				</td>
			</tr>
			
			<tr>
				<td>UPPER SECTION BONUS</td>
				<td>
					${game.bonusAndTotalGrid['uppersectionbonus']}
				</td>
			</tr>
		
			<tr>
				<td>MIN</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['min']}">
							<input type="radio" id="box" name="box" value="min">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['min']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td>MAX</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['max']}">
							<input type="radio" id="box" name="box" value="max">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['max']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td>MEDIUM SECTION TOTAL</td>
				<td>
					${game.bonusAndTotalGrid['mediumsectiontotal']}
				</td>
			</tr>
		
			<tr>
				<td>FULLHOUSE</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['fullhouse']}">
							<input type="radio" id="box" name="box" value="fullhouse">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['fullhouse']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td>FOUROFAKIND</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['fourofakind']}">
							<input type="radio" id="box" name="box" value="fourofakind">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['fourofakind']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td>STRAIGHT</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['straight']}">
							<input type="radio" id="box" name="box" value="straight">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['straight']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td>YAHTZEE</td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['yahtzee']}">
							<input type="radio" id="box" name="box" value="yahtzee">
						</c:when>
						<c:otherwise>
							${game.scoreGrid['yahtzee']}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td>LOWER SECTION TOTAL</td>
				<td>
					${game.bonusAndTotalGrid['lowersectiontotal']}
				</td>
			</tr>
			
		</table>
		
		
		
	</form>
	
	<c:if test="${ form.errors['fill'] != null }">
		<p>${ form.errors['fill'] }</p>
	</c:if>
	
</body>
</html>