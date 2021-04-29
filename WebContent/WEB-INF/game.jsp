<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="game.css">
<title>Game</title>
</head>
<body>
	
	<c:choose>
	
		<c:when test="${ game.finished }">
			<p>Game over</p>
		</c:when>
		
		<c:otherwise>
			
			<div class="rollingDices">
			
			<form method="post">
			
				<c:forEach var="dice" items="${game.currentHand}" varStatus="status">
					<label class="diceContainer">
						<input type="checkbox" id="dices" name="dices" value="${status.count}" ${ game.currentDiceRoll == 3 || game.currentDiceRoll == 0 ? "disabled" : "" }>
						<span class="checkmark ${ dice }" ></span>
					</label>
				</c:forEach>
				
				<button type="submit" id="roll" name="roll" value="roll" ${ game.currentDiceRoll == 3 ? "disabled" : "" }>
					<c:choose>
						<c:when test="${ game.currentDiceRoll == 0}">
							First Roll
						</c:when>
						<c:when test="${ game.currentDiceRoll == 1}">
							Second Roll
						</c:when>
						<c:when test="${ game.currentDiceRoll == 2}">
							Last Roll
						</c:when>
						<c:when test="${ game.currentDiceRoll == 3}">
							No more Roll
						</c:when>
					</c:choose> 
				</button>
				
			</form>
			
			<p class="error ${ form.errors['roll'] == null}">
				Error : ${ form.errors['roll'] }
			</p>
			
			</div>
			
		</c:otherwise>
	
	</c:choose>
	
	<form method="post">
	
		<table>
		
			<tr>
				<td><p class="figure">ACE</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['ace']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="ace">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['ace']}</p>
						</c:otherwise>
					</c:choose>
				</td>
				<td rowspan="16">
					<button type="submit" id="fill" name="fill" value="fill">Fill</button>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">TWO</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['two']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="two">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['two']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">THREE</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['three']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="three">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['three']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">FOUR</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['four']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="four">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['four']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">FIVE</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['five']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="five">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['five']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">SIX</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['six']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="six">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['six']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure total">UPPER SECTION TOTAL</p></td>
				<td>
					<p class="score total">${game.bonusAndTotalGrid['uppersectiontotal']}</p>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure total">UPPER SECTION BONUS</p></td>
				<td>
					<p class="score total">${game.bonusAndTotalGrid['uppersectionbonus']}</p>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">MIN</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['min']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="min">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['min']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">MAX</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['max']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="max">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['max']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure total">MEDIUM SECTION TOTAL</p></td>
				<td>
					<p class="score total">${game.bonusAndTotalGrid['mediumsectiontotal']}</p>
				</td>
			</tr>
		
			<tr>
				<td><p class="figure">FULLHOUSE</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['fullhouse']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="fullhouse">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['fullhouse']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure">FOUROFAKIND</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['fourofakind']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="fourofakind">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['fourofakind']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure">STRAIGHT</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['straight']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="straight">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['straight']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure">YAHTZEE</p></td>
				<td>
					<c:choose>
						<c:when test="${empty game.scoreGrid['yahtzee']}">
							<label class="boxContainer">
								<input type="radio" id="box" name="box" value="yahtzee">
								<span class="box"></span>
							</label>
						</c:when>
						<c:otherwise>
							<p class="score">${game.scoreGrid['yahtzee']}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<td><p class="figure total">LOWER SECTION TOTAL</p></td>
				<td>
					<p class="score total">${game.bonusAndTotalGrid['lowersectiontotal']}</p>
				</td>
			</tr>
			
		</table>
		
		
		
	</form>
	
	<c:if test="${ form.errors['fill'] != null }">
		<p>${ form.errors['fill'] }</p>
	</c:if>
	
</body>
</html>